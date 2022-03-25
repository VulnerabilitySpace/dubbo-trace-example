package org.apache.skywalking.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DubboProvider_Main {
    public static void main(String[] args) {
        System.out.printf("this is a message, data: %s", new String("a".getBytes()));
        System.out.println(System.out.printf("this is a message, data: %s", 1));
        SpringApplication.run(DubboProvider_Main.class, args);
    }

    public static void loop() {
        loop();
    }
}
