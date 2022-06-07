package com.southsystem.votingsytem.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

    @NotBlank(message = "tax id is mandatory")
    private String taxId;

    @NotNull(message = "candidate is mandatory")
    private Boolean candidate;
}
