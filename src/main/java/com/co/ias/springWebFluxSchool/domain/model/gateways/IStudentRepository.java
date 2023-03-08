package com.co.ias.springWebFluxSchool.domain.model.gateways;

import com.co.ias.springWebFluxSchool.domain.model.student.StudentRequest;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IStudentRepository {

    Mono<StudentRequest>  saveStudent(Mono<StudentRequest> studentRequestMono);

    Mono<StudentResponse> getStudentById(Long id);

    Flux<StudentResponse> getAllStudents();

    Mono<StudentRequest> updateStudent(Mono<StudentRequest>studentRequestMono);

}
