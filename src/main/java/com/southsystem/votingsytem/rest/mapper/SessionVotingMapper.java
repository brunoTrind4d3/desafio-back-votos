package com.southsystem.votingsytem.rest.mapper;

import com.southsystem.votingsytem.domain.entity.SessionVoting;
import com.southsystem.votingsytem.rest.model.CreateSessionVoting;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionVotingMapper {
    public static SessionVoting from(CreateSessionVoting subject) {
        return SessionVoting.builder()
                .description(subject.getDescription())
                .duration(subject.getDuration())
                .build();
    }
}
