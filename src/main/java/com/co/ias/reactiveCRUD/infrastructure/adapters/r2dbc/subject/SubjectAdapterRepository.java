package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.subject;

import com.co.ias.reactiveCRUD.domain.model.gateways.ISubjectRepository;
import com.co.ias.reactiveCRUD.domain.model.subject.Subject;
import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.entity.StudentDBO;
import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.entity.SubjectDBO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class SubjectAdapterRepository implements ISubjectRepository {
    public final ISubjectRepositoryAdapter iSubjectRepositoryAdapter;

    public SubjectAdapterRepository(ISubjectRepositoryAdapter iSubjectRepositoryAdapter) {
        this.iSubjectRepositoryAdapter = iSubjectRepositoryAdapter;
    }
    @Override
    public Subject saveSubject(Subject subject) {
        Mono<SubjectDBO> subjectDBOMono = Mono
                .just(SubjectDBO
                        .fromDomain(subject));

        Mono<SubjectDBO> savedSubjectDBOMono = subjectDBOMono
                .flatMap(iSubjectRepositoryAdapter::save);
        SubjectDBO subjectDBO = new SubjectDBO();
        savedSubjectDBOMono
                .subscribe(
                        x->{
                            System.out.println(x.getSubjectName());
                            subjectDBO.setSubjectId(x.getSubjectId());
                            subjectDBO.setSubjectName(x.getSubjectName());});
        return SubjectDBO
                .toDomain(subjectDBO);
    }

    @Override
    public List<Subject> getSubjects() {
        return null;
    }
}
