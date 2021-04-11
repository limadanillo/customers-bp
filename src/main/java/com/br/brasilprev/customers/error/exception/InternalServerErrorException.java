package com.br.brasilprev.customers.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class Internal Server Error Exception
 *
 * @author Danillo Lima
 * @since 09/04/2021
 */
@Slf4j
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

    private static final long serialVersionUID = -769147155483245021L;

    public InternalServerErrorException(String msg) {
        super(msg);
    }

    public InternalServerErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServerErrorException(Throwable cause) {
        super(cause);
    }

}