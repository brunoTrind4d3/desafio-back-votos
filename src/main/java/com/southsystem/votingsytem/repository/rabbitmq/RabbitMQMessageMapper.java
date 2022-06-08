package com.southsystem.votingsytem.repository.rabbitmq;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RabbitMQMessageMapper {
    public static SessionVoting from(SessionVoting subject) {
        return SessionVoting.builder()
                .id(subject.getId())
                .isClosed(true)
                .results(subject.getResults())
                .build();
    }
}
