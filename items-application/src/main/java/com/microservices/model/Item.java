package com.microservices.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class Item {
    @NotNull
    int id;

    @NotNull
    String name;

    @NotNull
    float price;

    int amount;

    public Item(int id, String name, float price, int amount){
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}
