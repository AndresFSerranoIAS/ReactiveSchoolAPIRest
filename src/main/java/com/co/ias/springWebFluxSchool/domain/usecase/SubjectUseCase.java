package com.co.ias.springWebFluxSchool.domain.usecase;

import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import reactor.core.publisher.Mono;

public class SubjectUseCase {
    private final ISubjectRepository iSubjectRepository;

    public SubjectUseCase(ISubjectRepository iSubjectRepository) {
        this.iSubjectRepository = iSubjectRepository;
    }
    public Mono<Subject> saveSubject(Mono<Subject> subjectMono){
        return iSubjectRepository.saveSubject(subjectMono);
    }
}
