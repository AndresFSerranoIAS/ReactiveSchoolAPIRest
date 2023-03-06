package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.entity;

import com.co.ias.reactiveCRUD.domain.model.student.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    public static StudentDBO fromDomain(Student student){
        return new StudentDBO(student.getStudentId().getValue(),
                student.getStudentName().getValue(),
                student.getStudentEmail().getValue(),
                student.getIdSubject().getValue());
    }
    public static Student toDomain(StudentDBO studentDBO){
        return new Student(new StudentId(studentDBO.getStudentId()),
                new StudentName(studentDBO.getStudentName()),
                new StudentEmail(studentDBO.getStudentEmail()),
                new IdSubject(studentDBO.getSubjectId()));
    }

}
