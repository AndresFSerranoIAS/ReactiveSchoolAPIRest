package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity;

import com.co.ias.springWebFluxSchool.domain.model.student.*;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="STUDENTS")
public class StudentDBO {
    @Id
    @Column("STUDENT_ID")
    private Long studentId;
    @Column("STUDENT_NAME")
    private String studentName;
    @Column("STUDENT_EMAIL")
    private String studentEmail;
    @Column("SUBJECT_ID")
    private Long subjectId;

    public static StudentDBO fromDomain(StudentRequest studentRequest){
        return new StudentDBO(studentRequest.getStudentId().getValue(),
                studentRequest.getStudentName().getValue(),
                studentRequest.getStudentEmail().getValue(),
                studentRequest.getSubjectId().getValue());
    }
    public static StudentRequest toDomain(StudentDBO studentDBO){
        return new StudentRequest(new StudentId(studentDBO.getStudentId()),
                new StudentName(studentDBO.getStudentName()),
                new StudentEmail(studentDBO.getStudentEmail()),
                new SubjectId(studentDBO.getSubjectId()));
    }

}
