package com.mugbya.message;

import com.mugbya.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ReceiveHandler {
    @RabbitListener(queues = {RabbitMQConfig.ORDER_QUEUE})
    public void receiveOrderMessage(String msg, Message message, Channel channel) throws IOException, InterruptedException {
        log.info("接受订单的消息: {}", msg);
        log.info(message.getMessageProperties().getDeliveryTag() + "");
        int a = 1 / 0;
//        try {
//            int a = 1 / 0;
//        }catch (Exception e) {
//            log.info(message.getMessageProperties().getDeliveryTag() + "");
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
//        }
//        channel.basicQos(120);
    }
}
