package com.sxt;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class SpringbootBabbitmqApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootBabbitmqApplication.class, args);
    }

}
