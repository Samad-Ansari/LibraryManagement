package com.dam.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@JsonIgnoreProperties(
        value = {"studentBooks"},
        allowGetters = false
)
public class Student {
    @Id
    private int roll;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @OneToMany(mappedBy = "student")
    private List<StudentBook> studentBooks = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private Account account;

    public List<StudentBook> getStudentBooks() {
        return studentBooks;
    }

    public void setStudentBooks(List<StudentBook> studentBooks) {
        this.studentBooks = studentBooks;
    }

    Student(){}

    public Student(int roll, String name, String password) {
        this.roll = roll;
        this.name = name;
        this.password = password;
        this.setAccount(null);
        this.setStudentBooks(null);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student { " +
                ", roll = " + roll +
                ", name = '" + name + '\'' +
                ", password = " + password +
                " }";
    }
}
