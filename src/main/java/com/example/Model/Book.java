package com.example.Model;

public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String category;
    private int noOfBooks;

    public Book(){

    }
    public Book(int bookId, String bookName, String author, String category, int noOfBooks) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.noOfBooks = noOfBooks;
    }
    public Book(String bookName, String author, String category, int noOfBooks) {
        this.bookName = bookName;
        this.author = author;
        this.category = category;
        this.noOfBooks = noOfBooks;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNoOfBooks() {
        return noOfBooks;
    }

    public void setNoOfBooks(int noOfBooks) {
        this.noOfBooks = noOfBooks;
    }
}
