package com.microservices.controller;

import com.microservices.model.CreateItem;
import com.microservices.model.Item;
import com.microservices.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("items")
public class ItemController {
    private ItemService itemService;
    private static final Logger log = Logger.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewItem(@Valid @RequestBody CreateItem createItem) {
        try {
            itemService.addNewItem(createItem);
            log.info("New item was added: " + createItem.toString());
        } catch (SQLException e) {
            log.error("Error with adding the item: " + e.toString());
        }
    }

    @GetMapping
    public ArrayList<Item> getAllItems() throws SQLException {
            return itemService.getAllItems();
    }

    @GetMapping(value = "/id/{id}")
    public Item getItemById(@PathVariable int id) {
        try {
            Item temp = itemService.getItemById(id);
            log.info("Item with id = " + id + " was found: " + temp.toString());
            return temp;
        } catch (SQLException e) {
            log.error("Item with id = " + id + "was not found: " + e.toString());
            return null;
        }
    }

    @PutMapping(value = "{id}/change/{amount}")
    public void changeItem(@PathVariable int id, @PathVariable int amount) {
        try {
            itemService.changeItemAmount(id, amount);
            log.info("To amount of item with " + id + " was  added" + amount);
        } catch (SQLException e) {
            log.error("Error with updating amount of item with id = " + id + ": " + e.toString());
        }
    }

    @GetMapping(value = "{name}")
    public ArrayList<Item> getItemsByName(@PathVariable String name) {
        try {
            ArrayList<Item> temp = itemService.getItemByName(name);
            log.info("Items included substring '" + name + "' was found");
            return temp;
        } catch (SQLException e) {
            log.error("Items included substring '" + name + "' was not found: " + e.toString());
            return null;
        }
    }
}


