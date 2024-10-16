package com.apirest.facturacionrest.utils;

import lombok.Data;

@Data
public class ApiResponseMsg {
    String message;
    Object data;

    public ApiResponseMsg(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
