package com.microservices.service;

import com.microservices.database.DBHelper;
import com.microservices.model.CreateItem;
import com.microservices.model.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemService {
    
    Item addNewItem(CreateItem createItem) throws SQLException;
    Item getItemById(int id) throws SQLException;
    Item changeItemAmount(int id, int amount) throws SQLException;
    ArrayList<Item> getItemByName(String name) throws SQLException;
    ArrayList<Item> getAllItems() throws SQLException;
}