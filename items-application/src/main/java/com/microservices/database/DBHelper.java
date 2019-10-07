package com.microservices.database;

import com.microservices.model.Item;
import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.ArrayList;

public class DBHelper {
    private static final String ID = "id";
    private static final String BD_NAME = "item_application/service";
    private static final String TABLE_NAME = "item";
    private static final String NAME = "name";
    private static final String PRICE = "price";
    private static final String AMOUNT = "amount";
    private static final String URL = "jdbc:mysql://localhost:3306/" + BD_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";
    private static Connection connection;


    public static void main(String[] args) {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
    }

    public void createItem(String name, float price, int amount) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAME + "," + PRICE + "," + AMOUNT + ") VALUES (" +
                name + "," + price + "," + amount + ");";
        statement.execute(sql);
    }

    public void changeItemAmount(int id, int amount) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT " + AMOUNT + " FROM " + TABLE_NAME + " WHERE " + ID + "=" + id + ";";
        ResultSet resultSet = statement.executeQuery(sql);
        int newAmount = amount;
        while (resultSet.next()){
            newAmount += resultSet.getInt(AMOUNT);
        }

        sql = "UPDATE " + TABLE_NAME + " SET " + AMOUNT + " = " + newAmount + " WHERE " + ID + "=" + id + ";";
        statement.execute(sql);
    }

    private ArrayList<Item> getItems(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(sql);
        ArrayList<Item> items= new ArrayList<>();

        if (rs != null) {
            while (rs.next()){
                Item item = new Item(rs.getInt(ID), rs.getString(NAME), rs.getFloat(PRICE), rs.getInt(AMOUNT));
                items.add(item);
            }
        }
        return items;
    }

    public Item searchItemById(int id) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID + " LIKE " + id + " && " + AMOUNT + " is not null";
        return getItems(sql).get(0);
    }

    public ArrayList<Item> showAllItems() throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + AMOUNT + " is not NULL";
        return getItems(sql);
    }

    public ArrayList<Item> searchItemsByName(String name) throws SQLException {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + NAME + " LIKE " + name + " && " + AMOUNT + " is not null";
        return getItems(sql);
    }
}