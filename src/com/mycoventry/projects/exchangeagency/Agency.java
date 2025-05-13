package com.mycoventry.projects.exchangeagency;

public class Agency {
    protected int id;
    protected String name;
    protected byte[] image; // Store image as Base64 encoded string
    protected String filepath;
    protected String base64Image;
    protected float price;
    protected String description;
    protected String product_condition;
    protected String category;
    protected String location;
    protected String feature;
    protected String brand;
    protected String username;


    public Agency() {
    }

    public Agency(int id) {
        this.id = id;
    }

    public Agency(int id, String name, float price, String description, String product_condition, String category, String location, String feature, String brand, String username, String filepath ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.product_condition = product_condition;
        this.category = category;
        this.location = location;
        this.feature = feature;
        this.brand = brand;
        this.username = username;
        this.filepath = filepath;
    }
    
    // Constructor for inserting a new agency (without id)
    public Agency(String name, float price, String description, String product_condition, String category, String location, String feature, String brand, String username, String filepath ) {
    	 this.name = name;
         this.price = price;
         this.description = description;
         this.product_condition = product_condition;
         this.category = category;
         this.location = location;
         this.feature = feature;
         this.brand = brand;
         this.username = username;
         this.filepath = filepath;
    }
    
  

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return this.image;
    }
    
    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
    
    public String getFilepath() {
    	return filepath;
    }
    
    public void setFilepath(String filepath) {
    	this.filepath = filepath;
    }
    public String getProduct_condition() {
        return product_condition;
    }

    public void setProduct_condition(String product_condition) {
        this.product_condition = product_condition;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getLocation() {
        return location;
    }

    public void setLoaction(String location) {
        this.location = location;
    }
    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
