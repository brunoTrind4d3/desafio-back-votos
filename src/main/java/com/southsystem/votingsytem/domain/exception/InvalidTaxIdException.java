package com.southsystem.votingsytem.domain.exception;

public class InvalidTaxIdException extends Exception {
    public InvalidTaxIdException(String taxIdNotFound) {
        super(taxIdNotFound);
    }
}
