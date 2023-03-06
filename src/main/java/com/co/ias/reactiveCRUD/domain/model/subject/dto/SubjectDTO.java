package com.co.ias.reactiveCRUD.domain.model.subject.dto;

import com.co.ias.reactiveCRUD.domain.model.subject.Subject;
import com.co.ias.reactiveCRUD.domain.model.subject.SubjectId;
import com.co.ias.reactiveCRUD.domain.model.subject.SubjectName;

public class SubjectDTO {
    private Long subjectId;
    private String subjectName;

    public SubjectDTO(Long subjectId, String subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public static SubjectDTO fromDomain(Subject subject){
        return new SubjectDTO(subject.getSubjectId().getValue(),
                subject.getSubjectName().getValue());
    }

    public static Subject toDomain(SubjectDTO subjectDTO){
        return new Subject(new SubjectId(subjectDTO.getSubjectId()),
                new SubjectName(subjectDTO.getSubjectName()));
    }
}
