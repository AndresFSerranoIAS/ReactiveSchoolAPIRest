package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.subject;

import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.SubjectDBO;
import org.springframework.stereotype.Repository;
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
}
