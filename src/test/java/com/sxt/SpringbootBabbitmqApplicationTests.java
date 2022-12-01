package com.sxt;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
class SpringbootBabbitmqApplicationTests {
    @Autowired(required = false)
    private RabbitTemplate rabbitTemplate;
    @Autowired(required = false)
    // 创建交换机和队列且绑定
    private AmqpAdmin amqpAdmin;
    @Test
    void creat() {
        amqpAdmin.declareExchange(new TopicExchange("fangexch.normal"));
        amqpAdmin.declareExchange(new TopicExchange("fangexch.dead"));
        HashMap map = new HashMap();
        map.put("x-dead-letter-exchange","fangexch.dead");
        map.put("x-dead-letter-routing-key","deadkey");
        map.put("x-message-ttl",8000);
        map.put("x-max-length",10);
        Queue queuenormal = new Queue("queue.normal",true,false,false,map);
        Queue queuedead = new Queue("queue.dead",true,false,false,null);
        Queue queuenormal2 = new Queue("queue.normal2",true,false,false,null);
        amqpAdmin.declareQueue(queuenormal);
        amqpAdmin.declareQueue(queuedead);
        amqpAdmin.declareQueue(queuenormal2);
        Binding binding = new Binding("queue.normal",Binding.DestinationType.QUEUE,"fangexch.normal","normalkey",null);
        Binding binding2 = new Binding("queue.dead",Binding.DestinationType.QUEUE,"fangexch.dead","deadkey",null);
        Binding binding3 = new Binding("queue.normal2",Binding.DestinationType.QUEUE,"fangexch.normal","normalkey.#",null);
        amqpAdmin.declareBinding(binding);
        amqpAdmin.declareBinding(binding2);
        amqpAdmin.declareBinding(binding3);
    }

    @Test
    void contextLoads() {
        HashMap map = new HashMap();
        map.put("a","aa");
        map.put("b","bb");
        map.put("c","cc");
        // 发送消息
        rabbitTemplate.convertAndSend("fangexch.normal","normalkey.*",map);
    }
    @Test
    void receive() {
        // 一次消费一条消息
        Object message = rabbitTemplate.receiveAndConvert("queue.normal2");
        System.out.println("消息  " + message);
    }


}
