package com.southsystem.votingsytem.repository.feign;

import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eligibilityToVote", url = "${eligibility.url}")
public interface EligibilityToVoteFeignClient {

    @GetMapping(value = "/users/{cpf}", produces = "application/json")
    Response getEligibilityToVote(@PathVariable(value = "cpf") String taxId);

}
