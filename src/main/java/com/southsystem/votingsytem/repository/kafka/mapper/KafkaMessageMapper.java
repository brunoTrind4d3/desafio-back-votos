package com.southsystem.votingsytem.repository.kafka.mapper;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import lombok.experimental.UtilityClass;

@UtilityClass
public class KafkaMessageMapper {
    public static SubjectVoting from(SubjectVoting subject) {
        return SubjectVoting.builder()
                .id(subject.getId())
                .isClosed(true)
                .results(subject.getResults())
                .build();
    }
}
