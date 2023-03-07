package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity;

import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectName;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="SUBJECTS")
@ToString
public class SubjectDBO {
    @Id
    @Column("SUBJECT_ID")
    private Long subjectId;
    @Column("SUBJECT_NAME")
    private String subjectName;

    public static SubjectDBO fromDomain(Subject subject) {
        return new SubjectDBO(subject.getSubjectId().getValue(),
                subject.getSubjectName().getValue());
    }

    public static Subject toDomain(SubjectDBO subjectDBO) {
        return new Subject(new SubjectId(subjectDBO.getSubjectId()),
                new SubjectName(subjectDBO.getSubjectName()));
    }

}