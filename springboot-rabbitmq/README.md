

springboot 2.3.0 下 publisher-confirms 发送消息确认方式已经被弃用

## 发布确认应答

springboot 2.3.0 以前 发布确认`publisher-confirm-type`只有两种方式，2.3.0开始有三种方式：none, simple, correlated


- none: 是禁用发布确认模式，是默认值
- correlated: 等于以前的 true 效果
- simple: 经测试有两种效果，
        其一效果和correlated值一样会触发回调方法，
        其二在发布消息成功后使用rabbitTemplate调用waitForConfirms或waitForConfirmsOrDie方法等待broker节点返回发送结果，根据返回结果来判定下一步的逻辑，要注意的点是waitForConfirmsOrDie方法如果返回false则会关闭channel，则接下来无法发送消息到broker;


## 接受确认应答

`spring.rabbitmq.listener.simple.acknowledge-mode` 也有是三个值

- none: 这才是自动确认
- auto: 根据情况确认(有点结合体的意味)
- manual: 手工确认

> 一定要注意 none 才是自动确认啊！！！


## Dead Letter Exchanges

[官网](https://www.rabbitmq.com/dlx.html)

中文大多都直译为：死信交换机。 `dead-letter-routing-key`死信路由键



> 注意：
>
> 1. 如果一个队列满足条件后存在死信消息，却没有配置死信交换机，那么消息最终被删除




延迟可以支持对Queue设置超时时间，也能在发送消息时对消息设置超时时间

前面通过 x-message-ttl 来设置;

后面通过如下格式设置：

```

        rabbitTemplate.convertAndSend(exchange, routingKey, msg,
                message -> {
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    message.getMessageProperties().setExpiration(delayTime + "");
                    return message;
                },
                new CorrelationData(UUID.randomUUID().toString())
        );

```

> 如果两个都设置，则消息优先匹配到时间最短


触发进入死信交换机的条件：

    1. 消息被拒绝。通过调用basic.reject或者basic.nack并且设置的requeue参数为false。
    2. 消息因为设置了TTL而过期。
    3. 消息进入了一条已经达到最大长度的队列


延迟队列多用于需要延迟工作的场景。最常见的是以下两种场景：

    - 延迟消费。比如：
        用户生成订单之后，需要过一段时间校验订单的支付状态，如果订单仍未支付则需要及时地关闭订单。
        用户注册成功之后，需要过一段时间比如一周后校验用户的使用情况，如果发现用户活跃度较低，则发送邮件或者短信来提醒用户使用。
    
    
    - 延迟重试。比如消费者从队列里消费消息时失败了，但是想要延迟一段时间后自动重试。
    
    作者：wooyoo
    链接：https://juejin.im/post/6844903512657100807
    来源：掘金
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


其他参考链接：

- [RabbitMQ延迟队列](https://segmentfault.com/a/1190000015369917)






