package com.microservices.service.impl;

import com.microservices.database.DBHelper;
import com.microservices.model.CreateItem;
import com.microservices.model.Item;
import com.microservices.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;

@Service
public class ItemServiceImpl implements ItemService {
    DBHelper dbHelper = new DBHelper();

    @Override
    public Item addNewItem(CreateItem createItem) throws SQLException {
        return dbHelper.createItem(createItem.name, createItem.price, createItem.amount);
    }

    @Override
    public Item getItemById(int id) throws SQLException {
        return dbHelper.searchItemById(id);
    }

    @Override
    public Item changeItemAmount(int id, int amount) throws SQLException {
        return dbHelper.changeItemAmount(id, amount);
    }

    @Override
    public ArrayList<Item> getItemByName(String name) throws SQLException {
        return dbHelper.searchItemsByName(name);
    }

    @Override
    public ArrayList<Item> getAllItems() throws SQLException {
        return dbHelper.showAllItems();
    }
}