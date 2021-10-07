package com.swoqe.librarianship.exception;

public class SwoqeException extends Exception {

    private static final long serialVersionUID = 1L;

    private SwoqeErrorCode errorCode;

    public SwoqeException() {
        super();
    }

    public SwoqeException(SwoqeErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public SwoqeException(String message, SwoqeErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public SwoqeException(String message, Throwable cause, SwoqeErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public SwoqeException(Throwable cause, SwoqeErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public SwoqeErrorCode getErrorCode() {
        return errorCode;
    }

}
