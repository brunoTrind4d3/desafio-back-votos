package com.southsystem.votingsytem.consumer;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class RabbitMQConsumer {

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receivedMessage(SessionVoting voting) {
        log.info("ReceivedMessage: {}", voting);
        if (voting.getResults() != null){
            log.info("Session voting is ending: {}", voting.getId());
            log.info("Votos sim: {}", voting.getResults().getYesVotes() + "%" );
            log.info("Votos não: {}", voting.getResults().getNoVotes() + "%" );
        }
    }
}
