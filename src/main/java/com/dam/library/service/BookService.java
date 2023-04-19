package com.dam.library.service;

import com.dam.library.dao.BookDao;
import com.dam.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookService {
    private final BookDao bookDao;

    BookService(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Transactional
    public void addBook(Book book){
        this.bookDao.addBook(book);
    }

    @Transactional
    public void updateBook(Book book){
        this.bookDao.updateBook(book);
    }

    @Transactional
    public List<Book> listBooks(){
        return this.bookDao.listBooks();
    }

    @Transactional
    public Book getBookById(int id){
        return this.bookDao.getBookById(id);
    }

    @Transactional
    public void removeBook(int id){
        this.bookDao.removeBook(id);
    }
}
