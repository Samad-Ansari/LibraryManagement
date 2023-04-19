package com.dam.library.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "StudentBook")
@Table(name = "students_books")
public class StudentBook {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private Book book;

    private Date issue_date = new Date();
    private Date return_date = null;

    private StudentBook() {}

    public StudentBook(Student student, Book book, Date date) {
        this.student = student;
        this.book = book;
        this.issue_date = date;
    }

    public Date getIssue_date() {
        return issue_date;
    }

    public void setIssue_date(Date issue_date) {
        this.issue_date = issue_date;
    }

    public Date getReturn_date() {
        return return_date;
    }

    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "StudentBook{" +
                "id=" + id +
                ", student=" + student +
                ", book=" + book +
                ", issue_date=" + issue_date +
                ", return_date=" + return_date +
                '}';
    }
}
