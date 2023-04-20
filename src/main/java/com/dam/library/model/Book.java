package com.dam.library.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int stock;

    @OneToMany( mappedBy = "book")
    private List<StudentBook> studentBooks = new ArrayList<>();

    public List<StudentBook> getStudentBooks() {
        return studentBooks;
    }

    public void setStudentBooks(List<StudentBook> studentBooks) {
        this.studentBooks = studentBooks;
    }

    public int getId() {
        return id;
    }

    public void addStock(){
        stock++;
    }

    public void minusStock(){
        stock--;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book { " +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", author = '" + author + '\'' +
                ", price = " + price +
                " }";
    }
}

