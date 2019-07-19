package ru.bestprice.backend.exlibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    @JsonProperty("name")
    private String name;

    @JsonProperty("nds")
    private int nds;

    @JsonProperty("sum")
    private int sum;

    @JsonProperty("quantity")
    private int quantity;

    @JsonProperty("paymentType")
    private int paymentType;

    @JsonProperty("price")
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNds() {
        return nds;
    }

    public void setNds(int nds) {
        this.nds = nds;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
