package com.co.ias.reactiveCRUD.domain.model.gateways;

import com.co.ias.reactiveCRUD.domain.model.subject.Subject;

import java.util.List;

public interface ISubjectRepository {

    Subject saveSubject(Subject subject);
    List<Subject> getSubjects();
}
