package ru.bestprice.backend.exlibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseCheck {

    @JsonProperty("data")
    private ResponseData data;

    @JsonProperty("code")
    private int code;

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
