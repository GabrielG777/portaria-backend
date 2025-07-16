package com.gabriel.portaria.dto;

public class ErroResponse {
    private String error;

    public ErroResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
