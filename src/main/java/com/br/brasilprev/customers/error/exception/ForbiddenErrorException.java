package com.br.brasilprev.customers.error.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Class Unauthorized Error Exception
 *
 * @author Danillo Lima
 * @since 09/04/2021
 */
@Slf4j
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenErrorException extends Throwable {

    private static final long serialVersionUID = -769147147883245023L;

    public ForbiddenErrorException(String msg) {
        super(msg);
    }

    public ForbiddenErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ForbiddenErrorException(Throwable cause) {
        super(cause);
    }

}