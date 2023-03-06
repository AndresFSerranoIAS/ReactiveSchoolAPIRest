package com.co.ias.reactiveCRUD.domain.model.subject;

public class Subject {

    private final SubjectId subjectId;
    private final SubjectName subjectName;

    public Subject(SubjectId subjectId, SubjectName subjectName) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
    }

    public SubjectId getSubjectId() {
        return subjectId;
    }

    public SubjectName getSubjectName() {
        return subjectName;
    }
}
