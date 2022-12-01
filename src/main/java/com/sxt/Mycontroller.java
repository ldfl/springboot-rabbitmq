package com.sxt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Mycontroller {
    @Autowired
    private MyThread myThread;
    @RequestMapping("/lius")
    public String demo() {
//        System.out.println("start");
//        // new Thread(() -> myThread.a()).start();
//        myThread.a();
//        System.out.println("end");
        return "ssss";
    }
}
