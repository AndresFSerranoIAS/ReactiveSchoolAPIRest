package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.student;
import com.co.ias.springWebFluxSchool.domain.model.student.StudentRequest;
import com.co.ias.springWebFluxSchool.domain.model.student.dto.StudentDTORequest;
import com.co.ias.springWebFluxSchool.domain.model.student.dto.StudentDTOResponse;
import com.co.ias.springWebFluxSchool.domain.usecase.StudentUseCase;
import com.co.ias.springWebFluxSchool.infrastructure.entrypoint.student.exceptions.StudentNotFoundException;
import com.co.ias.springWebFluxSchool.infrastructure.entrypoint.student.exceptions.SubjectNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class StudentHandler {

    private final StudentUseCase studentUseCase;

    public StudentHandler(StudentUseCase studentUseCase) {
        this.studentUseCase = studentUseCase;
    }

    public Mono<ServerResponse> saveStudent(ServerRequest request) {
        return studentUseCase.saveStudent(request.bodyToMono(StudentDTORequest.class).map(StudentDTORequest::toDomain))
                .flatMap(student -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("El estudiante " + student.getStudentName().getValue() + " se ha agregado satisfactoriamente a la base de datos."))
                .onErrorResume(SubjectNotFoundException.class, e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e.getMessage()))
                .onErrorResume(IllegalArgumentException.class, e -> ServerResponse.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e.getMessage()))
                .onErrorResume(DataIntegrityViolationException.class, e -> ServerResponse.status(HttpStatus.CONFLICT)
                        .bodyValue("Ya existe un registro con el nombre de ese estudiante"))
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("Error: Los campos de la materia no pueden ir nulos"));
    }

    public Mono<ServerResponse> getStudentById(ServerRequest request){
        Long id = Long.valueOf(request.pathVariable("id"));
        return  studentUseCase.getStudentById(id).flatMap(studentResponse -> ServerResponse.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(StudentDTOResponse.fromDomain(studentResponse))
        );
    }

    public Mono<ServerResponse> getAllStudents(ServerRequest request){
        return studentUseCase.getAllStudents()
                .map(StudentDTOResponse::fromDomain)
                .collectList()
                .flatMap(studentDTOResponses -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(studentDTOResponses))
                .switchIfEmpty(ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Collections.emptyList()));
    }

    public Mono<ServerResponse> updateStudent(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return studentUseCase.updateStudent(request.bodyToMono(StudentRequest.class), id)
                .flatMap(updatedStudent -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("Se ha actualizado correctamente el estudiante " + updatedStudent.getStudentName().getValue()))
                .onErrorResume(StudentNotFoundException.class, e -> ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e.getMessage()))
                .onErrorResume(SubjectNotFoundException.class, e -> ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(e.getMessage()));
    }


}
