package com.sentineliq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SentineliqChatbotWidgetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentineliqChatbotWidgetApplication.class, args);
    }
}