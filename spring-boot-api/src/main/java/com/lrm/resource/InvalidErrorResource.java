package com.lrm.resource;

public class InvalidErrorResource {

    private String message;
    private Object errors;

    public InvalidErrorResource(String message, Object errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "InvalidErrorResource{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
