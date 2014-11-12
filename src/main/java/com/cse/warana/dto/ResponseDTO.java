package com.cse.warana.dto;

import java.io.Serializable;

/**
 * Created by Sashika on Nov 11 0011, 2014.
 */
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 2660768158235092157L;

    private boolean success;
    private String message;
    private T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
