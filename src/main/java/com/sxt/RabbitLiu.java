//package com.sxt;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Component
//public class RabbitLiu {
//    @RabbitListener(queues = "liude")
//    public void a(Message m) {
//        byte[] body = m.getBody();
//        System.out.println("监听消息 " + new String(body));
//    }
//}
