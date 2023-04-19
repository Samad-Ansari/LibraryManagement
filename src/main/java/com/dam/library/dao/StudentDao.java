package com.dam.library.dao;

import com.dam.library.model.Book;
import com.dam.library.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDao {

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory factory) {
        sessionFactory = factory;
    }


    public void addStudent(Student student){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(student);
        System.out.println("Student saved successfully");
    }

    public int issueBook(int studentId, int bookId){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        Book book = session.get(Book.class, bookId);
        // check for stock
        if(book.getStock() <= 0){
            return 1;
        }
        student.addBooks(book);
        book.setStock(book.getStock()-1);
        System.out.println("Book added successfully");
        return 0;
    }

    public int returnBook(int studentId, int bookId){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        Book book = session.get(Book.class, bookId);
        return 0;
    }

    public void updateStudent(Student student){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(student);
        System.out.println("Student updated successfully");
    }

    @SuppressWarnings("unchecked")
    public List<Student> listStudent(){
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Student>) session.createQuery("from Student").list();
    }

    public Student getStudentById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
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
