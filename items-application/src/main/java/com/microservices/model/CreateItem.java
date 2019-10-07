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
public class CreateItem {
    @NotNull
    public String name;

    @NotNull
    public float price;

    @NotNull
    public int amount;

    public CreateItem(String name, float price, int amount){
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}