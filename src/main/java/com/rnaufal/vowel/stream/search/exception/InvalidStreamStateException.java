package com.rnaufal.vowel.stream.search.exception;

/**
 * Created by rnaufal on 17/11/16.
 */
public class InvalidStreamStateException extends RuntimeException {

    private static final long serialVersionUID = -6188074203275908046L;

    public InvalidStreamStateException() {
        super("Nao existem mais caracteres para serem processados");
    }
}
