package com.mugbya.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.ttl:60000}")
    private long makeCallExpire;

    // 业务队列
    public final static String ORDER_QUEUE = "order";
    // 死信接收队列
    public final static String DL_QUEUE = "dead-letter";

    // 业务交换机
    public final static String ORDER_EXCHANGE = "order-ex";

    // 死信(接收)交换机
    public final static String DLX_EXCHANGE = "dead-letter-ex";


    @Bean
    public FanoutExchange orderExchange() {
        return new FanoutExchange(ORDER_EXCHANGE);
    }

    @Bean
    public FanoutExchange dlExchange() {
        return new FanoutExchange(DLX_EXCHANGE);
    }



    @Bean
    public Queue orderQueue() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("x-dead-letter-exchange", DLX_EXCHANGE); // 指定死信送往的交换机
//        map.put("x-message-ttl", makeCallExpire); //
//         map.put("x-dead-letter-routing-key", "");  //指定死信的routingkey
//        return new Queue(ORDER_QUEUE, true, false, false, map);
        return new Queue(ORDER_QUEUE, true);
    }

    @Bean
    public Queue dlQueue() {
        return new Queue(DL_QUEUE);
    }


    /**
     * 当注释该绑定关系，会触发 returnCallback 回调
     * @return
     */
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderExchange());
    }


    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(dlQueue()).to(dlExchange());
    }
}
