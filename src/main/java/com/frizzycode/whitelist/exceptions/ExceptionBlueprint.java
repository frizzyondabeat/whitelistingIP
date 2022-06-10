package com.frizzycode.whitelist.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Builder
public record ExceptionBlueprint(String exception, HttpStatus httpStatus, ZonedDateTime time) {
}
