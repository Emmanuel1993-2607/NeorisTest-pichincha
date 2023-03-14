package com.manager.bankaccount.exception;

public class ErrorException extends Exception {
    private static final long serialVersionUID = 1L;

    public ErrorException() {

    }

    public ErrorException(String mensaje) {
        super(mensaje);
    }


}
