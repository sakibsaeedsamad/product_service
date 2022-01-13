package com.scube.product_service.exception;

import org.springframework.http.HttpStatus;

public class ProductServiceException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public ProductServiceException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
