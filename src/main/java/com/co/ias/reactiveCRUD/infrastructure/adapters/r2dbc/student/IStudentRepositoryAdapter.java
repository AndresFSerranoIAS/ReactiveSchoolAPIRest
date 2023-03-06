package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.student;

import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.entity.StudentDBO;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;


public interface IStudentRepositoryAdapter extends ReactiveCrudRepository<StudentDBO,Long> {

}
