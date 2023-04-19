package com.dam.library.service;

import com.dam.library.dao.StudentDao;
import com.dam.library.model.Book;
import com.dam.library.model.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Transactional
    public void addStudent(Student student){
        this.studentDao.addStudent(student);
    }

    @Transactional
    public void updateStudent(Student student){
        this.studentDao.updateStudent(student);
    }

    @Transactional
    public List<Student> listStudent(){
        return this.studentDao.listStudent();
    }

    @Transactional
    public Student getStudentById(int id){
        return this.studentDao.getStudentById(id);
    }

    @Transactional
    public void removeStudent(int id){
        this.studentDao.removeStudent(id);
    }

    @Transactional
    public String issueBook(int studentId, int bookId){
        if(this.studentDao.issueBook(studentId, bookId) == 1){
            return "Book already exist !";
        }
        return "Book added successfully !";
    }
}
