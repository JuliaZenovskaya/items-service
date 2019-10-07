package com.microservices.controller;

import com.microservices.model.CreateItem;
import com.microservices.model.Item;
import com.microservices.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "add")
    @ResponseStatus(HttpStatus.CREATED)
    void addNewItem(CreateItem createItem) {
        try {
            itemService.addNewItem(createItem);
            log.info("Добавлен новый товар");
        } catch (SQLException e) {
            log.error("Ошибка добавления товара в БД: " + e.toString());
        }
    }

    @GetMapping(value = "{id}")
    Item getItemById(int id) {
        try {
            Item temp = itemService.getItemById(id);
            log.info("Найден товар по id = " + id);
            return temp;
        } catch (SQLException e) {
            log.error("Ошибка поиска товара с id = " + id + ": " + e.toString());
            return null;
        }
    }

    @PutMapping(value = "{id}/change/{amount}")
    void changeItem(int id, int amount) {
        try {
            itemService.changeItemAmount(id, amount);
            log.info("К количеству товара с id = " + id + " добавлено " + amount);
        } catch (SQLException e) {
            log.error("Ошибка изменения количества товара с id = " + id + ": " + e.toString());
        }
    }

    @GetMapping(value = "{name}")
    ArrayList<Item> getItemsByName(@PathVariable String name) {
        try {
            ArrayList<Item> temp = itemService.getItemByName(name);
            log.info("Найдены товары, содержащие в названии подстроку '" + name + "'");
            return temp;
        } catch (SQLException e) {
            log.error("Ошибка поиска товаров: " + e.toString());
            return null;
        }
    }
}

