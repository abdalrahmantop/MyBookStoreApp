package com.example.mybookstore;

public class DAFactory {
    public  IBookDA getInstance(){

        return new BookDA();
    }

}