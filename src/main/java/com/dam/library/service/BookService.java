package com.dam.library.service;

import com.dam.library.dao.BookDao;
import com.dam.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final BookDao bookDao;

    BookService(BookDao bookDao){
        this.bookDao = bookDao;
    }

    @Transactional
    public String addBook(Book book){

        String message = errorCheck(book);
        if(!message.equals("1")){
            return message;
        }
        this.bookDao.addBook(book);
        return "Book added Successfully";
    }

    @Transactional
    public String updateBook(Book book){
        String message = errorCheck(book);
        if(!message.equals("1")){
            return message;
        }
        this.bookDao.updateBook(book);
        return "Book updated successfully";
    }

    @Transactional
    public List<Book> listBooks(){
        List<Book> listBook = this.bookDao.listBooks();
        return listBook;
    }

    @Transactional
    public Book getBookById(int id){
        return this.bookDao.getBookById(id);
    }

    @Transactional
    public String removeBook(int id){
        Book book = this.bookDao.getBookById(id);
        if(book == null){
            return "Book not exist";
        }
        this.bookDao.removeBook(id);
        return "Book Removed Successfully";
    }

    String errorCheck(Book book){
        if(book.getAuthor() == null){
            return "Author is required";
        }
        if(book.getTitle() == null){
            return "Title is required";
        }
        if(book.getStock() == 0){
            return "Stock is required";
        }
        if(book.getStock() < 0){
            return "Stock must be positive";
        }
        if(book.getPrice() == 0){
            return "Price is required";
        }
        if(book.getPrice() < 0){
            return "Price must be positive";
        }
        return "1";
    }
}
