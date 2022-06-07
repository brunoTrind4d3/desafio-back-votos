package com.southsystem.votingsytem.domain.repository;

import com.southsystem.votingsytem.domain.exception.InvalidTaxIdException;

public interface EligibilityToVoteRepository {
    boolean isEligible(String cpf) throws InvalidTaxIdException;
}
