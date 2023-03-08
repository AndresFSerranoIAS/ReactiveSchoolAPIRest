package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.student;

import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.StudentDBO;
import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.StudentDBOResponseCustom;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStudentAdapterRepository extends ReactiveCrudRepository<StudentDBO,Long> {
    @Query("SELECT s.student_name, s.student_id, s.student_email, s.subject_id, sb.subject_name FROM STUDENTS s, SUBJECTS sb WHERE s.subject_id = sb.subject_id AND s.student_id = :id LIMIT 1")
    Mono<StudentDBOResponseCustom> getStudentById(Long id);

    @Query("SELECT s.student_name, s.student_id, s.student_email, s.subject_id, sb.subject_name " +
            "FROM STUDENTS s " +
            "JOIN SUBJECTS sb ON s.subject_id = sb.subject_id")
    Flux<StudentDBOResponseCustom> getAllStudents();

}
