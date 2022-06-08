package com.southsystem.votingsytem.consumer;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(SessionVoting voting) {
        log.info("ReceivedMessage: {}", voting);
        if (voting.getResults() != null) {
            log.info("Session voting is over: {}", voting.getId());
            log.info("Votes to yes: {}", voting.getResults().getYesVotes() + "%");
            log.info("Votes to no: {}", voting.getResults().getNoVotes() + "%");
        }
    }
}
