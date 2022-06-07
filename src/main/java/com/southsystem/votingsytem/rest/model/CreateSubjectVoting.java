package com.southsystem.votingsytem.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class CreateSubjectVoting {

    private String description;
    private Integer duration;

}
