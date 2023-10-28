package com.ibssoln.shoppers.domain.exception;

public class ShoppersException extends Exception {

    private static final long serialVersionUID = 1L;

    private String details;
    private int errorCode = -1;

    public ShoppersException(){
        super();
    }

    public ShoppersException(final Throwable cause){
        super(cause);
    }

    public ShoppersException(final String message, final Throwable cause){
        super(message, cause);
    }

    public ShoppersException(final String message, final Throwable cause, boolean enableSuppression, boolean writeableStackTrace){
        super(message, cause, enableSuppression, writeableStackTrace);
    }

    public ShoppersException(final String message){
        super(message);
    }

    public ShoppersException(final String message, final String details){
        super(message);
        this.details = details;
    }

    public ShoppersException(final String message, int errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public ShoppersException(final String message, final int errorCode, final String details){
        super(message);
        this.errorCode = errorCode;
        this.details = details;
    }



}
