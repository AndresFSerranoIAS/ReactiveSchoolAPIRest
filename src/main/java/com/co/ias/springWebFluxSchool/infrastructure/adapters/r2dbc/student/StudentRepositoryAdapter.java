package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.student;

import com.co.ias.springWebFluxSchool.domain.model.gateways.IStudentRepository;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentRequest;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentResponse;
import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.StudentDBO;
import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.StudentDBOResponseCustom;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class StudentRepositoryAdapter implements IStudentRepository {

    public final IStudentAdapterRepository iStudentAdapterRepository;

    public StudentRepositoryAdapter(IStudentAdapterRepository iStudentAdapterRepository) {
        this.iStudentAdapterRepository = iStudentAdapterRepository;
    }

    @Override
    public Mono<StudentRequest> saveStudent(Mono<StudentRequest> studentRequestMono) {
        return studentRequestMono.map(StudentDBO::fromDomain).flatMap(iStudentAdapterRepository::save).map(StudentDBO::toDomain);
    }

    @Override
    public Mono<StudentResponse> getStudentById(Long id) {
        return iStudentAdapterRepository.getStudentById(id).map(StudentDBOResponseCustom::toDomain);
    }

    @Override
    public Flux<StudentResponse> getAllStudents() {
        return iStudentAdapterRepository.getAllStudents().map(StudentDBOResponseCustom::toDomain);
    }

    @Override
    public Mono<StudentRequest> updateStudent(Mono<StudentRequest> studentRequestMono) {
        return studentRequestMono.map(StudentDBO::fromDomain).flatMap(iStudentAdapterRepository::save).map(StudentDBO::toDomain);
    }
}
