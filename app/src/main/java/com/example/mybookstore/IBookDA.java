package com.example.mybookstore;

import java.util.ArrayList;

public interface IBookDA {
    String[] getCats();
    ArrayList<Book> getBooksCategory(String cat);

    ArrayList<Book> getBooksSearch(String text);
}