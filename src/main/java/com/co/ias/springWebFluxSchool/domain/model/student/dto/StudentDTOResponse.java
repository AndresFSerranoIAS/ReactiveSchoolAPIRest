package com.co.ias.springWebFluxSchool.domain.model.student.dto;

import com.co.ias.springWebFluxSchool.domain.model.student.StudentRequest;
import com.co.ias.springWebFluxSchool.domain.model.subject.dto.SubjectDTO;

public class StudentDTOResponse {
    private Long studentId;

    private String studentName;
    private String studentEmail;
    private SubjectDTO subjectDTO;

    public StudentDTOResponse(Long studentId, String studentName, String studentEmail, SubjectDTO subjectDTO) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.subjectDTO = subjectDTO;
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

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }

    private StudentDTOResponse fromDomain(StudentRequest student){
        return new StudentDTOResponse(student.getStudentId().getValue(),
                student.getStudentName().getValue(),
                student.getStudentEmail().getValue(),
null
                );
    }
}
