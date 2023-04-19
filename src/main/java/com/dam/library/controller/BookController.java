package com.dam.library.controller;

import com.dam.library.model.Book;
import com.dam.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping
    public ResponseEntity<?> listBooks(){
        System.out.println("List of Book");
        List<Book> bookList = this.bookService.listBooks();
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>  addBook(@RequestBody Book book){
        this.bookService.addBook(book);
        return new ResponseEntity<>("added successfully", HttpStatus.OK);
    }

    @RequestMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable(name = "bookId") int id){
        Book book = this.bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteBook(@PathVariable(name = "bookId") int id){
        this.bookService.removeBook(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }
}
