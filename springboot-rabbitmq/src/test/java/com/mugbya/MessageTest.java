package com.mugbya;

import com.mugbya.config.RabbitMQConfig;
import com.mugbya.message.RabbitMQSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageTest {
    @Autowired
    private RabbitMQSender sender;

    @Test
    public void testSendMessage(){
        String message = "{\"appKey\":\"cjhe31hh56imn2ciid3o4nygebgtktliu4n56xyu\",\"appName\":\"测试\",\"appSecret\":\"2f2896876322b44da94bb295d003af84\",\"createTime\":\"2020-01-01 19:00:00\",\"userId\": 598775}";

        sender.sendMessage(RabbitMQConfig.ORDER_EXCHANGE, null, message);

    }
}
