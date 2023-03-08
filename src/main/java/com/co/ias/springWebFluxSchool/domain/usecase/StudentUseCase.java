package com.co.ias.springWebFluxSchool.domain.usecase;

import com.co.ias.springWebFluxSchool.domain.model.gateways.IStudentRepository;
import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.model.student.*;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import com.co.ias.springWebFluxSchool.infrastructure.entrypoint.exceptions.StudentNotFoundException;
import com.co.ias.springWebFluxSchool.infrastructure.entrypoint.exceptions.SubjectNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class StudentUseCase {

    public final IStudentRepository iStudentRepository;
    public final ISubjectRepository iSubjectRepository;

    public StudentUseCase(IStudentRepository iStudentRepository, ISubjectRepository iSubjectRepository) {
        this.iStudentRepository = iStudentRepository;
        this.iSubjectRepository = iSubjectRepository;
    }

    public Mono<StudentRequest> saveStudent(Mono<StudentRequest> studentRequestMono) {
        return studentRequestMono
                .flatMap(studentRequest ->
                        iSubjectRepository
                                .getSubjectById(studentRequest.getSubjectId().getValue())
                                .switchIfEmpty(Mono.error(new SubjectNotFoundException("No se encontró ninguna materia con el ID " + studentRequest.getSubjectId().getValue())))
                                .flatMap(subject -> iStudentRepository.saveStudent(Mono.just(new StudentRequest(new StudentId(studentRequest.getStudentId().getValue()),
                                        new StudentName(studentRequest.getStudentName().getValue()),
                                       new StudentEmail(studentRequest.getStudentEmail().getValue()),
                                        new SubjectId(subject.getSubjectId().getValue())))))
                );
    }

    public Mono<StudentResponse> getStudentById(Long id){
        return iStudentRepository.getStudentById(id);
    }

    public Flux<StudentResponse> getAllStudents(){
        return iStudentRepository.getAllStudents();
    }

    public Mono<StudentRequest> updateStudent(Mono<StudentRequest> studentRequestMono, Long id) {
        return iStudentRepository.findStudentById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException("No se ha encontrado ningún estudiante con el ID " + id)))
                .flatMap(existingStudent ->
                                studentRequestMono.flatMap(updatedStudent ->
                                    iSubjectRepository.getSubjectById(updatedStudent.getSubjectId().getValue())
                                            .switchIfEmpty(Mono.error(new SubjectNotFoundException("No se ha encontrado ninguna materia con el ID " + updatedStudent.getSubjectId().getValue())))
                                            .flatMap(subject ->
                                                    iStudentRepository.saveStudent(Mono.just(new StudentRequest(
                                                                new StudentId(existingStudent.getStudentId().getValue()),
                                                                new StudentName(updatedStudent.getStudentName().getValue()),
                                                                new StudentEmail(updatedStudent.getStudentEmail().getValue()),
                                                                new SubjectId(subject.getSubjectId().getValue())))))
                                ));
    }

    public Mono<Boolean> deleteStudent(Long id){
        return iStudentRepository.getStudentById(id)
                .flatMap(existingSubject -> iStudentRepository.deleteStudent(id).thenReturn(true))
                .defaultIfEmpty(false);
    }

    public Mono<StudentRequest> quitSubjectFromStudent(Long id) {
        return iStudentRepository.findStudentById(id)
                .switchIfEmpty(Mono.error(new StudentNotFoundException("No se ha encontrado ningún estudiante con el ID " + id)))
                .flatMap(existingStudent -> {
                    if (existingStudent.getSubjectId().getValue() == null) {
                        return Mono.error(new IllegalArgumentException("Este estudiante no está matriculado en ninguna materia"));
                    }
                    return iStudentRepository.saveStudent(Mono.just(new StudentRequest(
                            new StudentId(existingStudent.getStudentId().getValue()),
                            new StudentName(existingStudent.getStudentName().getValue()),
                            new StudentEmail(existingStudent.getStudentEmail().getValue()),
                            new SubjectId(null)
                    )));
                });
    }

    public Flux<StudentResponse> findStudentsBySubjectId(Long id){
        return iStudentRepository.findStudentsBySubjectId(id);

    }

    public Flux<StudentRequest> findAllStudentsInDB(){
        return iStudentRepository.findAllStudentsInDB();
    }


}
