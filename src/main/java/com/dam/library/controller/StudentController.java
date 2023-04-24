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

    @PostMapping
    public ResponseEntity<?>  addStudent(@RequestBody Student student) throws IOException {
        String message = this.studentService.addStudent(student);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping(value="/edit/{studentId}")
    public ResponseEntity<?>  updateStudent(@PathVariable("studentId") int id, @RequestBody Student student){
        Student temp = this.studentService.getStudentById(id);
        if(temp == null){
            return new ResponseEntity<>("student not exist", HttpStatus.OK);
        }
        if(student.getName() != null){
            temp.setName(student.getName());
        }
        if(student.getPassword() != null){
            temp.setPassword(student.getPassword());
        }
        this.studentService.updateStudent(temp);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }

    @RequestMapping("/all")
    public ResponseEntity<?>  getStudent() throws IOException {
        List<Student> students = this.studentService.listStudent();
        System.out.println(students);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<?>  getStudentById(@RequestParam("roll") int rollno) throws IOException {
        Student student = this.studentService.getStudentById(rollno);
        if(student == null){
            return new ResponseEntity<>("Student not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteStudent(@RequestParam(name = "roll") int roll) throws IOException {
        this.studentService.removeStudent(roll);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/issue")
    public ResponseEntity<?> issueBook(@PathVariable(name = "roll") int roll, @RequestParam(name = "bookId") int id, @RequestParam("date") @DateTimeFormat(pattern="dd-MM-yyyy") Date date){
        String message = this.studentService.issueBook(roll, id, date);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/return/{roll}", method = RequestMethod.PUT)
    public ResponseEntity<?> returnBook(@PathVariable(name = "roll") int roll, @RequestParam(name = "bookId") int id, @RequestParam("date") @DateTimeFormat(pattern="dd-MM-yyyy") Date date){
        String message = this.studentService.returnBook(roll, id, date);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/*")
    public ResponseEntity<?> handle() {
        return new ResponseEntity<>("Invalid URL", HttpStatus.BAD_REQUEST);
    }

}
