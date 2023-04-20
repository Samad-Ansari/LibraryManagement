package com.dam.library.service;

import com.dam.library.dao.BookDao;
import com.dam.library.dao.LibrarianDao;
import com.dam.library.model.Book;
import com.dam.library.model.Librarian;
import com.dam.library.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LibrarianService {
    private final LibrarianDao librarianDao;

    LibrarianService(LibrarianDao librarianDao){
        this.librarianDao = librarianDao;
    }

    @Transactional
    public void addLibrarian(Librarian librarian){
        this.librarianDao.addLibrarian(librarian);
    }

    @Transactional
    public void updateLibrarian(Librarian librarian){
        this.librarianDao.updateLibrarian(librarian);
    }

    @Transactional
    public List<Librarian> listLibrarians(){
        return this.librarianDao.listLibrarians();
    }

    @Transactional
    public Librarian getLibrarianById(int id){
        return this.librarianDao.getLibrarianById(id);
    }

    @Transactional
    public void removeLibrarian(int id){
        this.librarianDao.removeLibrarian(id);
    }


    @Transactional
    public void addStudent(Student student){
        this.librarianDao.addStudent(student);
    }

    @Transactional
    public void updateStudent(Student student){
        this.librarianDao.updateStudent(student);
    }

    @Transactional
    public List<Student> listStudent(){
        return this.librarianDao.listStudent();
    }

    @Transactional
    public Student getStudentById(int id){
        Student student =  this.librarianDao.getStudentById(id);
        return student;
    }

    @Transactional
    public void removeStudent(int id){
        this.librarianDao.removeStudent(id);
    }

}
