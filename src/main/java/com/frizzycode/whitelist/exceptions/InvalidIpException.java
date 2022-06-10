package com.frizzycode.whitelist.exceptions;

public class InvalidIpException extends RuntimeException{
    public InvalidIpException(String message) {
        super(message);
    }
}
