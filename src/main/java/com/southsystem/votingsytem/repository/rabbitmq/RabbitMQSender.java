package com.southsystem.votingsytem.repository.rabbitmq;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class RabbitMQSender {

    @Autowired
    AmqpTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingkey;

    public void send(SessionVoting sessionVoting) {
        rabbitTemplate.convertAndSend(exchange, routingkey, sessionVoting);
        System.out.println("Send msg = " + sessionVoting);
    }

}
