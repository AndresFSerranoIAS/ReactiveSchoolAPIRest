package com.co.ias.springWebFluxSchool.domain.model.student;

import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;

public class StudentRequest {

    private final StudentId studentId;
    private final StudentName studentName;
    private final StudentEmail studentEmail;

    private final SubjectId subjectId;

    public StudentRequest(StudentId studentId, StudentName studentName, StudentEmail studentEmail, SubjectId subjectId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.subjectId = subjectId;
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

    public SubjectId getSubjectId() {
        return subjectId;
    }
}
