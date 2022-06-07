package com.southsystem.votingsytem.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Builder
@Data
public class CreateSubjectVoting {

    @NotBlank(message = "description is mandatory")
    private String description;

    @NotNull(message = "duration is mandatory")
    private Integer duration;

}
