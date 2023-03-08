package com.co.ias.springWebFluxSchool.domain.model.student.dto;

import com.co.ias.springWebFluxSchool.domain.model.student.StudentRequest;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentResponse;
import com.co.ias.springWebFluxSchool.domain.model.subject.dto.SubjectDTO;

public class StudentDTOResponse {
    private Long studentId;

    private String studentName;
    private String studentEmail;
    private SubjectDTO subject;

    public StudentDTOResponse(Long studentId, String studentName, String studentEmail, SubjectDTO subject) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.subject = subject;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public SubjectDTO getSubject() {
        return subject;
    }

    public static StudentDTOResponse fromDomain(StudentResponse studentResponse){
        return new StudentDTOResponse(studentResponse.getStudentId().getValue(),
                studentResponse.getStudentName().getValue(),
                studentResponse.getStudentEmail().getValue(),
                SubjectDTO.fromDomain(studentResponse.getSubject()));
    }
}
