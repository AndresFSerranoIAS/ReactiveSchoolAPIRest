package com.co.ias.springWebFluxSchool.domain.model.student.dto;

import com.co.ias.springWebFluxSchool.domain.model.student.StudentRequest;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentEmail;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentId;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentName;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;

public class StudentDTORequest {
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Long subjectId;

    public StudentDTORequest(Long studentId, String studentName, String studentEmail, Long subjectId) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.subjectId = subjectId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    private StudentDTORequest fromDomain(StudentRequest student){
        return new StudentDTORequest(student.getStudentId().getValue(),
                student.getStudentName().getValue(),
                student.getStudentEmail().getValue(),
                student.getSubjectId().getValue());
    }
    private StudentRequest toDomain(StudentDTORequest studentDTORequest){
        return new StudentRequest(new StudentId(studentDTORequest.getStudentId()),
                new StudentName(studentDTORequest.getStudentName()),
                new StudentEmail(studentDTORequest.getStudentEmail()),
                new SubjectId(studentDTORequest.getSubjectId()));
    }
}
