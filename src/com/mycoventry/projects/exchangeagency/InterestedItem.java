package com.mycoventry.projects.exchangeagency;

import java.sql.Timestamp;

public class InterestedItem {
    private int id;
    private String username;
    private Timestamp interestedDate;
    private String name;
    private double price;
    private String brand;
    private String base64Image; // Base64 encoded image
    private String filepath;

    public InterestedItem(int id, String username, Timestamp interestedDate, String name, double price, String brand, String base64Image, String filepath) {
        this.id = id;
        this.username = username;
        this.interestedDate = interestedDate;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.base64Image = base64Image;
        this.filepath = filepath;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getInterestedDate() {
        return interestedDate;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public String getFilepath() {
        return filepath;
    }
}
