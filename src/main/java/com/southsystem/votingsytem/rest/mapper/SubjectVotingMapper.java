package com.southsystem.votingsytem.rest.mapper;

import com.southsystem.votingsytem.domain.entity.SubjectVoting;
import com.southsystem.votingsytem.rest.model.CreateSubjectVoting;


public class SubjectVotingMapper {
    public static SubjectVoting from(CreateSubjectVoting subject) {
        return SubjectVoting.builder()
                .description(subject.getDescription())
                .duration(subject.getDuration())
                .build();
    }
}
