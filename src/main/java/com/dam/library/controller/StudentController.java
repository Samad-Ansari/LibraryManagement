package com.dam.library.controller;

import com.dam.library.model.Book;
import com.dam.library.model.Student;
import com.dam.library.service.BookService;
import com.dam.library.service.StudentService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @PostMapping
    public ResponseEntity<?>  addStudent(@RequestBody Student student) throws IOException {
        this.studentService.addStudent(student);
        return new ResponseEntity<>("added successfullly", HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?>  updateStudent(@RequestBody Student student){
        Student temp = this.studentService.getStudentById(student.getRoll());
        if(temp == null){
            return new ResponseEntity<>("student not exist", HttpStatus.OK);
        }
        if(student.getName() == null){
            student.setName(temp.getName());
        }
        if(student.getPassword() == null){
            student.setPassword(temp.getPassword());
        }
        this.studentService.updateStudent(student);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }


    @RequestMapping
    public ResponseEntity<?>  getStudent() throws IOException {
        List<Student> students = this.studentService.listStudent();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @RequestMapping("/{roll}")
    public ResponseEntity<?>  getStudentById(@PathVariable("roll") int rollno) throws IOException {
        Student student = this.studentService.getStudentById(rollno);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @RequestMapping(value = "/{roll}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteStudent(@PathVariable(name = "roll") int roll) throws IOException {
        this.studentService.removeStudent(roll);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/{roll}", method = RequestMethod.PUT)
    public ResponseEntity<?> addBooks(@PathVariable(name = "roll") int roll, @RequestParam(name = "bookId") int id){
        String message = this.studentService.issueBook(roll, id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
