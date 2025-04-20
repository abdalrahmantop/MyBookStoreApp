package com.example.mybookstore;

public class CartItem {
    String nameBook ;

    double Price ;

    int  Quantity ;

    double totalprice ;

    int imageResource;

    public CartItem(String nameBook, double price, int quantity, int imageResource) {
        this.nameBook = nameBook;
        Price = price;
        Quantity = quantity;
        this.imageResource = imageResource;
    }

    public double getTotalprice() {
        return  getQuantity()*getPrice();
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
