package com.dam.library.dao;

import com.dam.library.model.Book;
import com.dam.library.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    private final SessionFactory sessionFactory;
    BookDao(SessionFactory factory){
        this.sessionFactory = factory;
    }

    public void addBook(Book book){
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        System.out.println("Book saved successfully = " + book);
    }

    public void updateBook(Book book){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        System.out.println("Book updated successfully = " + book);
    }

    @SuppressWarnings("unchecked")
    public List<Book> listBooks(){
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Book>) session.createQuery("from Book").list();
    }

    public Book getBookById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        System.out.println("Book loaded successfully");
        return book;
    }

    public void removeBook(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, id);
        if(null != book){
            session.delete(book);
        }
        System.out.println("Book deleted successfully = " + book);
    }

}
