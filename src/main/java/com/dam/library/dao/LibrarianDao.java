package com.dam.library.dao;

import com.dam.library.model.Account;
import com.dam.library.model.Book;
import com.dam.library.model.Librarian;
import com.dam.library.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LibrarianDao {

    private final SessionFactory sessionFactory;

    public LibrarianDao(SessionFactory factory) {
        sessionFactory = factory;
    }

    public void addLibrarian(Librarian librarian){
        Session session = this.sessionFactory.getCurrentSession();
        System.out.println("Librarian saved successfully = " + librarian);
    }

    public void updateLibrarian(Librarian librarian){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(librarian);
        System.out.println("Librarian updated successfully = " + librarian);
    }

    @SuppressWarnings("unchecked")
    public List<Librarian> listLibrarians(){
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Librarian>) session.createQuery("from Librarian").list();
    }

    public Librarian getLibrarianById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Librarian librarian = session.get(Librarian.class, id);
        System.out.println("Librarian loaded successfully");
        return librarian;
    }

    public void removeLibrarian(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Librarian librarian = session.load(Librarian.class, id);
        if(null != librarian){
            session.delete(librarian);
        }
        System.out.println("Librarian deleted successfully = " + librarian);
    }

    // account created repeatedly
    public void addStudent(Student student){
        Session session = this.sessionFactory.getCurrentSession();
        Account account = new Account();
        session.persist(account);
        student.setAccount(account);
        session.persist(student);
        System.out.println("Student saved successfully");
    }


    public void updateStudent(Student student){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(student);
        System.out.println("Student updated successfully");
    }

    @SuppressWarnings("unchecked")
    public List<Student> listStudent(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Student> studentList = (List<Student>) session.createQuery("from Student").list();
        return studentList;
    }

    public Student getStudentById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        return student;
    }

    public void removeStudent(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.load(Student.class, id);
        if(student != null){
            session.delete(student);
            System.out.println("Student deleted successfully");
        }
    }


}
