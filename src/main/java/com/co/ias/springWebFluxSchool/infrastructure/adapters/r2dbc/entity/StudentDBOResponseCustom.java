package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity;

import com.co.ias.springWebFluxSchool.domain.model.student.*;
import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectName;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StudentDBOResponseCustom {

    private Long studentId;

    private String studentName;

    private String studentEmail;

    private Long subjectId;
    private String subjectName;

    public static StudentResponse toDomain(StudentDBOResponseCustom studentDBOResponse){
        return new StudentResponse(new StudentId(studentDBOResponse.getStudentId()),
                new StudentName(studentDBOResponse.getStudentName()),
                new StudentEmail(studentDBOResponse.getStudentEmail()),
                new Subject(new SubjectId(studentDBOResponse.getSubjectId()),new SubjectName(studentDBOResponse.getSubjectName())));
    }
}
