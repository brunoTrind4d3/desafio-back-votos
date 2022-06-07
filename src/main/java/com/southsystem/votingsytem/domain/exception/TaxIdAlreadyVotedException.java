package com.southsystem.votingsytem.domain.exception;

public class TaxIdAlreadyVotedException extends Exception {
    public TaxIdAlreadyVotedException(String message){
        super(message);
    }
}
