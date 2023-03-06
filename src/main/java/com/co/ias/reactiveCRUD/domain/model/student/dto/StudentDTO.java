package com.co.ias.reactiveCRUD.domain.model.student.dto;

import com.co.ias.reactiveCRUD.domain.model.student.*;

public class StudentDTO {

    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Long subjectId;

    public StudentDTO() {
    }

    public StudentDTO(Long studentId, String studentName, String studentEmail, Long subjectId) {
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

    public static StudentDTO fromDomain(Student student){
        return new StudentDTO(student.getStudentId().getValue(),
                student.getStudentName().getValue(),
                student.getStudentEmail().getValue(),
                student.getIdSubject().getValue());
    }

    public static Student toDomain(StudentDTO studentDTO){
        return new Student(new StudentId(studentDTO.getStudentId()),
                new StudentName(studentDTO.getStudentName()),
                new StudentEmail(studentDTO.getStudentEmail()),
                new IdSubject(studentDTO.getSubjectId()));
    }

}
