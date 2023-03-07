package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.subject;

import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.SubjectDBO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class SubjectRepositoryAdapter implements ISubjectRepository {
    public final ISubjectAdapterRepository iSubjectAdapterRepository;
    public SubjectRepositoryAdapter(ISubjectAdapterRepository iSubjectAdapterRepository) {
        this.iSubjectAdapterRepository = iSubjectAdapterRepository;
    }
    @Override
    public Mono<Subject> saveSubject(Mono<Subject> subjectMono) {
        return subjectMono.map(SubjectDBO::fromDomain).flatMap(iSubjectAdapterRepository::save).map(SubjectDBO::toDomain);
    }

    @Override
    public Flux<Subject> getSubjects() {
        return iSubjectAdapterRepository.findAll().map(SubjectDBO::toDomain);
    }

    @Override
    public Mono<Subject> getSubjectById(Long id) {
        return iSubjectAdapterRepository.findById(id).map(SubjectDBO::toDomain);
    }

    @Override
    public Mono<Subject> updateSubject(Mono<Subject> subjectMono, Long id) {
        return subjectMono.map(SubjectDBO::fromDomain).flatMap(iSubjectAdapterRepository::save).map(SubjectDBO::toDomain);
    }
}
