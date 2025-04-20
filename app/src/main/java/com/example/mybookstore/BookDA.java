package com.example.mybookstore;

import java.util.ArrayList;

public class BookDA
        implements IBookDA{
    private ArrayList<Book> books = new ArrayList<>();

    public ArrayList<Book> getSampleBooks() {

        books.add(new Book("رياض الصالحين", "النووي", "أحاديث في الأخلاق والآداب", R.drawable.riyad, 30.0, 10 , "religious"));
        books.add(new Book("مدارج السالكين", "ابن القيم", "شرح لمنازل السائرين", R.drawable.madarej, 30.0, 5  , "religious"));
        books.add(new Book("القبس الوهاج", "ابن حجر", "شرح فقهي شافعي", R.drawable.mnhaj, 15.0, 3 , "religious" ));
        books.add(new Book("رقائق القرآن", "السكران", "تأملات قرآنية", R.drawable.rqaeq, 20.0, 15 , "religious"));
        books.add(new Book("تاريخ الاسلام", ": محمود شاكر ", " كتاب عن تاريخ الإسلام منذ البداية وحتى العصر الحديث ", R.drawable.islam, 20.0, 8 , "historic"));

        return books;
    }
    @Override
    public String[] getCats() {
        String[] cats = {"All","religious", "historic"};
        return cats;

    }

    @Override
    public ArrayList<Book> getBooksCategory(String cat) {
        ArrayList<Book> result = new ArrayList<>();
        getSampleBooks();
        for(Book b: books){
            if(b.getTypeCategory().equals(cat)){
                result.add(b);
            }
        }
        return result;

    }

    @Override
    public ArrayList<Book> getBooksSearch(String text) {
        ArrayList<Book> result = new ArrayList<>();
        getSampleBooks();
        for(Book b: books){
            if(b.getName().equals(text)){
                result.add(b);
            }
        }
        return result;

    }

}