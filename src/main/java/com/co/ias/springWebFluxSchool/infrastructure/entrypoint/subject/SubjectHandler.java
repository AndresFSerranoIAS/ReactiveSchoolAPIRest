package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.subject;

import com.co.ias.springWebFluxSchool.domain.model.subject.dto.SubjectDTO;
import com.co.ias.springWebFluxSchool.domain.usecase.SubjectUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class SubjectHandler {

    private SubjectUseCase subjectUseCase;

    public SubjectHandler(SubjectUseCase subjectUseCase) {
        this.subjectUseCase = subjectUseCase;
    }
    public Mono<ServerResponse> saveSubject(ServerRequest request){
        Mono<SubjectDTO> subjectDTOMono = request.bodyToMono(SubjectDTO.class);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(subjectUseCase.saveSubject(subjectDTOMono.map(SubjectDTO::toDomain)).map(SubjectDTO::fromDomain),SubjectDTO.class);
    }

}