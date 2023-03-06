package com.co.ias.reactiveCRUD.domain.model.student;

public class Student {

    private final StudentId studentId;
    private final StudentName studentName;
    private final StudentEmail studentEmail;
    private final IdSubject idSubject;

    public Student(StudentId studentId, StudentName studentName, StudentEmail studentEmail, IdSubject idSubject) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.idSubject = idSubject;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public StudentName getStudentName() {
        return studentName;
    }

    public StudentEmail getStudentEmail() {
        return studentEmail;
    }

    public IdSubject getIdSubject() {
        return idSubject;
    }
}
