package com.dam.library.controller;

import com.dam.library.model.Book;
import com.dam.library.model.Student;
import com.dam.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("books")
public class BookController {

    private final BookService bookService;

    BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value = "/all")
    public ResponseEntity<?> listBooks(){
        List<Book> bookList = this.bookService.listBooks();
        if(bookList == null){
            return new ResponseEntity<>("Book not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>  addBook(@RequestBody Book book){
        String message = this.bookService.addBook(book);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @RequestMapping(value="/{bookId}", method = RequestMethod.PUT)
    public ResponseEntity<?>  updateBook(@PathVariable("bookId") int bookId, @RequestBody Book book){
        Book temp = this.bookService.getBookById(bookId);
        if(temp == null){
            return new ResponseEntity<>("book not exist", HttpStatus.NOT_FOUND);
        }
        if(book.getStock() != 0){
            temp.setStock(book.getStock());
        }
        if(book.getPrice() != 0){
            temp.setPrice(book.getPrice());
        }
        if(book.getAuthor() != null){
            temp.setAuthor(book.getAuthor());
        }
        if(book.getTitle() != null){
            temp.setTitle(book.getTitle());
        }
        temp.setId(bookId);
        String message = this.bookService.updateBook(temp);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @RequestMapping
    public ResponseEntity<?> getBookById(@RequestParam(name = "bookId") int id){
        Book book = this.bookService.getBookById(id);
        if(book == null){
            return new ResponseEntity<>("Book not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteBook(@PathVariable(name = "bookId") int id){
        String message = this.bookService.removeBook(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> pageNotFound() {
        return new ResponseEntity<>("Page Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/*")
    public ResponseEntity<?> handle() {
        return new ResponseEntity<>("Invalid URL", HttpStatus.BAD_REQUEST);
    }

}
