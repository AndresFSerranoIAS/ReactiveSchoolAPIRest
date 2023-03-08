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
        return iSubjectRepository.saveSubject(iSubjectRepository.getSubjectById(id).flatMap(existingSubject -> subjectMono.map(newSubject -> {
            Subject updatedSubject = new Subject(
                    new SubjectId(id),
                    new SubjectName(newSubject.getSubjectName().getValue()));
            return updatedSubject;
        })).switchIfEmpty(Mono.empty()));
    }

    public Mono<Boolean> deleteSubject(Long id) {
        return iSubjectRepository.getSubjectById(id)
                .flatMap(existingSubject -> iSubjectRepository.deleteSubject(id).thenReturn(true))
                .defaultIfEmpty(false);
    }

}
