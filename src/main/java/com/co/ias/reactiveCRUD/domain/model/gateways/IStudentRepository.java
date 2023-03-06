package com.co.ias.reactiveCRUD.domain.model.gateways;

import com.co.ias.reactiveCRUD.domain.model.student.Student;

import java.util.List;

public interface IStudentRepository {

    Student saveStudent(Student student);
    List<Student> getStudents();
}
