package com.frizzycode.whitelist.exceptions;

public class IpNotFoundException extends RuntimeException{
    public IpNotFoundException(String message) {
        super(message);
    }
}
