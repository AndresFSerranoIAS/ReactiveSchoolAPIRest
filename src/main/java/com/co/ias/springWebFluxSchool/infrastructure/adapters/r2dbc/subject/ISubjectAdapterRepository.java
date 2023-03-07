package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.subject;

import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.SubjectDBO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ISubjectAdapterRepository extends ReactiveCrudRepository<SubjectDBO,Long> {
}
