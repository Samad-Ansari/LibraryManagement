package com.dam.library.service;

import com.dam.library.dao.StudentDao;
import com.dam.library.model.Book;
import com.dam.library.model.Student;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Transactional
    public String addStudent(Student student){
        Student student1 = this.studentDao.getStudentById(student.getRoll());
        if(student1 != null){
            return "Student with Roll Number " + student1.getRoll() + " Already Exist";
        }
        this.studentDao.addStudent(student);
        return "Student Added Successfully";
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
        Student student =  this.studentDao.getStudentById(id);
        return student;
    }

    @Transactional
    public void removeStudent(int id){
        this.studentDao.removeStudent(id);
    }

    @Transactional
    public String issueBook(int studentId, int bookId, Date date){
        if(this.studentDao.issueBook(studentId, bookId, date) == 1){
            return "Book is out of stock !";
        }
        return "Book added successfully !";
    }

    @Transactional
    public String returnBook(int studentId, int bookId, Date date){
        if(this.studentDao.returnBook(studentId, bookId, date) == 1){
            return "No book issue record available";
        }
        return "Book returned successfully";
    }
}
