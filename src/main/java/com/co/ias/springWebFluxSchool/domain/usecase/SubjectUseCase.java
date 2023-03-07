package com.co.ias.springWebFluxSchool.domain.usecase;

import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectName;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class SubjectUseCase {
    private final ISubjectRepository iSubjectRepository;

    public SubjectUseCase(ISubjectRepository iSubjectRepository) {
        this.iSubjectRepository = iSubjectRepository;
    }
    public Mono<Subject> saveSubject(Mono<Subject> subjectMono){
        return iSubjectRepository.saveSubject(subjectMono);
    }

    public Flux<Subject> getSubjects(){
        return  iSubjectRepository.getSubjects();
    }
    public Mono<Subject> getSubjectById(Long id){
        return iSubjectRepository.getSubjectById(id);
    }
    public Mono<Subject> updateSubject(Mono<Subject> subjectMono, Long id) {
        Mono<Subject> existingSubjectMono = iSubjectRepository.getSubjectById(id);
        Mono<Subject> updatedSubjectMono = existingSubjectMono
                .flatMap(existingSubject -> subjectMono.map(newSubject -> {
                    Subject updatedSubject = new Subject(
                            new SubjectId(id),
                            new SubjectName(newSubject.getSubjectName().getValue()));
                    return updatedSubject;
                }));
        return iSubjectRepository.saveSubject(updatedSubjectMono);
    }
}
