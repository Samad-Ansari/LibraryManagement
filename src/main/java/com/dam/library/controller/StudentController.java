package com.dam.library.controller;

import com.dam.library.model.Book;
import com.dam.library.model.Student;
import com.dam.library.service.BookService;
import com.dam.library.service.StudentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(value = "/{roll}")
    public ResponseEntity<?> issueBook(@PathVariable(name = "roll") int roll, @RequestParam(name = "bookId") int id, @RequestParam("date") @DateTimeFormat(pattern="dd-MM-yyyy") Date date){
        String message = this.studentService.issueBook(roll, id, date);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{roll}", method = RequestMethod.PUT)
    public ResponseEntity<?> returnBook(@PathVariable(name = "roll") int roll, @RequestParam(name = "bookId") int id, @RequestParam("date") @DateTimeFormat(pattern="dd-MM-yyyy") Date date){
        String message = this.studentService.returnBook(roll, id, date);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
