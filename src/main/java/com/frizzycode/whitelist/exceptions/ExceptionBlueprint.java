package com.frizzycode.whitelist.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


@Builder
@AllArgsConstructor
public record ExceptionBlueprint(String exception, HttpStatus httpStatus, ZonedDateTime time) {
}
