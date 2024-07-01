/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.datastorage;

/**
 *
 * @author rizal
 */
public class Main {

    public static void main(String[] args) {

        DataStorage memoryStorage = new MemoryStorage1();
        memoryStorage.writeData("Data stored in memory");
        System.out.println("Memory storage: " + memoryStorage.readData());

        DataStorage fileStorage = new FileStorage("data.txt");
        fileStorage.writeData("Data stored in file");
        System.out.println("File storage: " + fileStorage.readData());

        DaatabaseStorage databaseStorage = new DaatabaseStorage("database.db");
        databaseStorage.writeData("Data stored in database");
        System.out.println("Database storage: " + databaseStorage.readData());
    }
}