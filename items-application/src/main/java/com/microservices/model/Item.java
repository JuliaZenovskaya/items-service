package com.microservices.model;

public class Item {
    int id;
    String name;
    float price;
    int amount;

    public Item(int id, String name, float price, int amount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
