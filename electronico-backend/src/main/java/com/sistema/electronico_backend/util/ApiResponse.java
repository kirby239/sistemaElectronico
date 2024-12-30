package com.sistema.electronico_backend.util;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ApiResponse<T> {
    private  int status;
    private  String message;
    private  T data;
    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters y setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
