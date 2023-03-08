package com.co.ias.springWebFluxSchool.domain.model.student;

import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;

public class StudentResponse {

    private final StudentId studentId;
    private final StudentName studentName;

    private final StudentEmail studentEmail;

    private final Subject subject;

    public StudentResponse(StudentId studentId, StudentName studentName, StudentEmail studentEmail, Subject subject) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.subject = subject;
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

    public Subject getSubject() {
        return subject;
    }
}
