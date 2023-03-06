package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.subject;

import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.entity.SubjectDBO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISubjectRepositoryAdapter extends ReactiveCrudRepository<SubjectDBO,Long>{
}
