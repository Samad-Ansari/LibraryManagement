package com.dam.library.controller;

import com.dam.library.model.Librarian;
import com.dam.library.model.Student;
import com.dam.library.service.LibrarianService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("librarians")
public class LibrarianController {

    private final LibrarianService librarianService;

    LibrarianController(LibrarianService librarianService){
        this.librarianService = librarianService;
    }

    @RequestMapping
    public ResponseEntity<?> listLibrarians(){
        List<Librarian> librarianList = this.librarianService.listLibrarians();
        System.out.println(librarianList);
        return new ResponseEntity<>(librarianList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?>  addLibrarian(@RequestBody Librarian librarian){
        this.librarianService.addLibrarian(librarian);
        return new ResponseEntity<>("added successfully", HttpStatus.OK);
    }


    @RequestMapping(value = "/{librarianId}", method = RequestMethod.PUT)
    public ResponseEntity<?>  updateLibrarian(@PathVariable("librarianId") int id, @RequestBody Librarian librarian){
        Librarian temp = this.librarianService.getLibrarianById(id);
        if(temp == null){
            return new ResponseEntity<>("librarian not exist", HttpStatus.OK);
        }
        librarian.setEmpId(id);
        if(librarian.getName() == null){
            librarian.setName(temp.getName());
        }
        if(librarian.getPassword() == null){
            librarian.setPassword(temp.getPassword());
        }

        this.librarianService.updateLibrarian(librarian);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }


    @RequestMapping("/{librarianId}")
    public ResponseEntity<?> getLibrarianById(@PathVariable(name = "librarianId") int id){
        Librarian librarian = this.librarianService.getLibrarianById(id);
        return new ResponseEntity<>(librarian, HttpStatus.OK);
    }

    @RequestMapping(value = "/{librarianId}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteLibrarian(@PathVariable(name = "librarianId") int id){
        this.librarianService.removeLibrarian(id);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }


    @PostMapping(value = "/student")
    public ResponseEntity<?>  addStudent(@RequestBody Student student) throws IOException {
        System.out.println(student);
        this.librarianService.addStudent(student);
        return new ResponseEntity<>("added successfully", HttpStatus.OK);
    }



    @RequestMapping(value="/student/{studentId}", method = RequestMethod.PUT)
    public ResponseEntity<?>  updateStudent(@PathVariable("studentId") int id, @RequestBody Student student){
        Student temp = this.librarianService.getStudentById(id);
        if(temp == null){
            return new ResponseEntity<>("student not exist", HttpStatus.OK);
        }
        if(student.getName() == null){
            student.setName(temp.getName());
        }
        if(student.getPassword() == null){
            student.setPassword(temp.getPassword());
        }
        this.librarianService.updateStudent(student);
        return new ResponseEntity<>("updated successfully", HttpStatus.OK);
    }


    @RequestMapping("/student")
    public ResponseEntity<?>  getStudent() throws IOException {
        List<Student> students = this.librarianService.listStudent();
        System.out.println(students);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @RequestMapping("/student/{roll}")
    public ResponseEntity<?>  getStudentById(@PathVariable("roll") int rollno) throws IOException {
        Student student = this.librarianService.getStudentById(rollno);
        if(student == null){
            return new ResponseEntity<>("Student not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @RequestMapping(value = "/student/{roll}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteStudent(@PathVariable(name = "roll") int roll) throws IOException {
        this.librarianService.removeStudent(roll);
        return new ResponseEntity<>("deleted successfully", HttpStatus.OK);
    }

}
