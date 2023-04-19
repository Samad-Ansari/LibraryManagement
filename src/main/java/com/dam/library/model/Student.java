package com.dam.library.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    private int roll;
    private String name;
    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "student")
    private List<StudentBook> studentBooks = new ArrayList<>();

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
