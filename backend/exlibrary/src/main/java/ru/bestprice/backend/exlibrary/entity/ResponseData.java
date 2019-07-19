package ru.bestprice.backend.exlibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseData {

    @JsonProperty("json")
    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}