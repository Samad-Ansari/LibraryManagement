package com.dam.library.dao;

import com.dam.library.model.Account;
import com.dam.library.model.Book;
import com.dam.library.model.Student;
import com.dam.library.model.StudentBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class StudentDao {
    final int BOOK_ISSUE_LIMIT_DAY = 20;

    private final SessionFactory sessionFactory;

    public StudentDao(SessionFactory factory) {
        sessionFactory = factory;
    }

    // account created repeatedly
    public void addStudent(Student student){
        Session session = this.sessionFactory.getCurrentSession();
        Account account = new Account();
        session.persist(account);
        student.setAccount(account);
        session.persist(student);
        System.out.println("Student saved successfully");
    }


    public void updateStudent(Student student){
        Session session = this.sessionFactory.getCurrentSession();
        session.update(student);
        System.out.println("Student updated successfully");
    }

    @SuppressWarnings("unchecked")
    public List<Student> listStudent(){
        Session session = this.sessionFactory.getCurrentSession();
        List<Student> studentList = (List<Student>) session.createQuery("from Student").list();
        return studentList;
    }

    public Student getStudentById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        return student;
    }

    public void removeStudent(int id){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.load(Student.class, id);
        if(student != null){
            session.delete(student);
            System.out.println("Student deleted successfully");
        }
    }

    public int issueBook(int studentId, int bookId, Date date){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        Book book = session.get(Book.class, bookId);
        // check for stock
        if(book.getStock() <= 0){
            return 1;
        }
        book.minusStock();
        StudentBook sb = new StudentBook(student, book, date);
        student.getStudentBooks().add(sb);
        student.getAccount().setNoBorrowedBook(1);
        book.getStudentBooks().add(sb);
        session.save(sb);
        System.out.println("Book added successfully");
        return 0;
    }

    public int returnBook(int studentId, int bookId, Date date){
        Session session = this.sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, studentId);
        List<StudentBook> studentBooks = student.getStudentBooks();
        StudentBook studentBook = null;
        for(StudentBook sb : studentBooks){
            if(sb.getBook().getId() == bookId && sb.getReturn_date() == null){
                studentBook = sb;
                break;
            }
        }
        if(studentBook == null){
            return 1;
        }

        long dayLate = ((studentBook.getIssue_date().getTime() - date.getTime()) / (1000 * 60 * 60 * 24)) % 365 + 1 + BOOK_ISSUE_LIMIT_DAY;
        if(dayLate < 0){
            student.getAccount().addFine(Math.abs(dayLate));
        }
        System.out.println();
        student.getAccount().setNoReturnedBook(1);
        student.getAccount().setNoBorrowedBook(-1);
        studentBook.getBook().addStock();
        studentBook.setReturn_date(date);

        return 0;
    }


}
