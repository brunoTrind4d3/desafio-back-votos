package com.southsystem.votingsytem.repository;

import com.google.gson.GsonBuilder;
import com.southsystem.votingsytem.domain.exception.InvalidTaxIdException;
import com.southsystem.votingsytem.domain.repository.EligibilityToVoteRepository;
import com.southsystem.votingsytem.repository.feign.EligibilityToVoteFeignClient;
import com.southsystem.votingsytem.repository.model.EligibilityModel;
import feign.Response;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.io.IOException;

import static net.logstash.logback.marker.Markers.append;

@Slf4j
@Repository
public class EligibilityToVoteRepositoryImpl implements EligibilityToVoteRepository {

    private static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    @Autowired
    EligibilityToVoteFeignClient eligibilityToVoteFeignClient;

    @Override
    public boolean isEligible(String cpf) throws InvalidTaxIdException {
        try {
            Response resp = null;
            resp = this.eligibilityToVoteFeignClient.getEligibilityToVote(cpf);

            log.info("REQUEST", append("path", resp.request().url()));
            if (resp.status() == HttpStatus.OK.value()) {
                log.info("RESPONSE", append("body", resp.body().toString()), append("status", String.valueOf(resp.status())));
                var builder = new GsonBuilder();
                var decoder = new GsonDecoder(builder.create());
                EligibilityModel response = null;
                response = (EligibilityModel) decoder.decode(resp, EligibilityModel.class);
                return ABLE_TO_VOTE.equals(response.getStatus());
            }else if(resp.status() == HttpStatus.NOT_FOUND.value()){
                throw new InvalidTaxIdException("taxId not found");
            }
        } catch (IOException e) {
            log.error("RESPONSE", append("ERROR", e.getMessage()));
        }
        return false;
    }

}
