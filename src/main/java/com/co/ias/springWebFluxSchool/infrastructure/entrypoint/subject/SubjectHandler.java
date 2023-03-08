package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.subject;

import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.domain.model.subject.dto.SubjectDTO;
import com.co.ias.springWebFluxSchool.domain.usecase.SubjectUseCase;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Component
public class SubjectHandler {

    private SubjectUseCase subjectUseCase;

    public SubjectHandler(SubjectUseCase subjectUseCase) {
        this.subjectUseCase = subjectUseCase;
    }
    public Mono<ServerResponse> saveSubject(ServerRequest request) {
        return subjectUseCase.saveSubject(request.bodyToMono(SubjectDTO.class).map(SubjectDTO::toDomain))
                .flatMap(subject -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("La materia " + subject.getSubjectName().getValue() + " se ha agregado satisfactoriamente a la base de datos."))
                .onErrorResume(DataIntegrityViolationException.class, e -> ServerResponse.status(HttpStatus.CONFLICT).bodyValue("Ya existe un registro con ese nombre de materia"))
                .onErrorResume(IllegalArgumentException.class, e -> ServerResponse.badRequest().bodyValue("Error: " + e.getMessage()))
                .switchIfEmpty(ServerResponse.badRequest().bodyValue("Error: Los campos de la materia no pueden ir nulos"));
    }
    public Mono<ServerResponse> getSubjects(ServerRequest request) {
        return subjectUseCase.getSubjects()
                .map(SubjectDTO::fromDomain)
                .collectList()
                .flatMap(subjectDTOs -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(subjectDTOs))
                .switchIfEmpty(ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Collections.emptyList()))
                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("No se ha podido acceder a la base de datos"));
    }

    public Mono<ServerResponse> getSubjectById(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return subjectUseCase.getSubjectById(id)
                .flatMap(subject -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(SubjectDTO.fromDomain(subject)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(String.format("No se ha encontrado ninguna materia con el ID %d",id)))
                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("No se ha podido acceder a la base de datos"));
    }

    public Mono<ServerResponse> updateSubject(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(SubjectDTO.class)
                .map(SubjectDTO::toDomain)
                .flatMap(subject -> subjectUseCase.updateSubject(Mono.just(subject), id))
                .flatMap(subject -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(SubjectDTO.fromDomain(subject)))
                .switchIfEmpty(ServerResponse.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(String.format("No se ha encontrado ninguna materia con el ID %d", id)))
                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("No se ha podido acceder a la base de datos"));
    }

    public Mono<ServerResponse> deleteSubject(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        return subjectUseCase.deleteSubject(id)
                .flatMap(result -> {
                    if (!result) {
                        return ServerResponse
                                .status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(String.format("No se ha encontrado ninguna materia asociada al ID %d",id));
                    }
                    return ServerResponse
                            .status(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(String.format("Se ha borrado correctamente la materia con ID %d",id));
                })
                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue("No se ha podido acceder a la base de datos"));
    }
}