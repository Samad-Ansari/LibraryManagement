package com.dam.library.model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(nullable = false)
    private int noBorrowedBook;
    @Column(nullable = false)
    private int noReturnedBook;
    @Column(nullable = false)
    private int fineAmount;

    @OneToOne(mappedBy = "account")
    private Student student;

    public Account() {
        this.noBorrowedBook = 0;
        this.noReturnedBook = 0;
        this.fineAmount = 0;
    }

    public void addFine(long dayLate){
        fineAmount += dayLate;
    }

    public int getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(int fineAmount) {
        this.fineAmount = fineAmount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoBorrowedBook() {
        return noBorrowedBook;
    }

    public void setNoBorrowedBook(int noBorrowedBook) {
        this.noBorrowedBook += noBorrowedBook;
    }

    public int getNoReturnedBook() {
        return noReturnedBook;
    }

    public void setNoReturnedBook(int noReturnedBook) {
        this.noReturnedBook += noReturnedBook;
    }

    @Override
    public String toString() {
        return "Account { " +
                "id = " + id +
                ", noBorrowedBook = " + noBorrowedBook +
                ", noReturnedBook = " + noReturnedBook +
                ", fineAmount = " + fineAmount +
                " }";
    }
}
