package com.co.ias.springWebFluxSchool.domain.model.gateways;

import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISubjectRepository {
    Mono<Subject> saveSubject(Mono<Subject> subjectMono);
    Flux<Subject> getSubjects();
    Mono<Subject> getSubjectById(Long id);
    Mono<Subject> updateSubject(Mono<Subject> subjectMono,Long id);
}
