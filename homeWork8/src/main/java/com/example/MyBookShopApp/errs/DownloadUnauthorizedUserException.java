package com.example.MyBookShopApp.errs;

public class DownloadUnauthorizedUserException extends Exception {
    private String message;

    public DownloadUnauthorizedUserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
