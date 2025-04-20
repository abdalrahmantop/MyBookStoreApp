package com.example.mybookstore;

public class Book {

    String name;

    String author ;
    String description;
    int imageResource;
    double price;
    int quantity;

    String  TypeCategory ;

    public Book(String name, String author, String description, int imageResource, double price, int quantity , String TypeC) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.imageResource = imageResource;
        this.price = price;
        this.quantity = quantity;
        this.TypeCategory = TypeC ;

    }

    public String getTypeCategory() {
        return TypeCategory;
    }

    public void setTypeCategory(String typeCategory) {
        TypeCategory = typeCategory;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
