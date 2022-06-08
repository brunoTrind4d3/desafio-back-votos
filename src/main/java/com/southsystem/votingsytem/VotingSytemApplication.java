package com.southsystem.votingsytem;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@EnableScheduling
@EnableFeignClients
@EnableRabbit
public class VotingSytemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingSytemApplication.class, args);
    }

}
