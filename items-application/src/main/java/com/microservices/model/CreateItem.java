package com.microservices.model;

public class CreateItem {
    public String name;
    public float price;
    public int amount;

    public CreateItem(String name, float price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}