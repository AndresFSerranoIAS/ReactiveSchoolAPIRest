package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.subject;

import com.co.ias.reactiveCRUD.domain.model.subject.dto.SubjectDTO;
import com.co.ias.reactiveCRUD.domain.usecases.SubjectUseCase;
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
        return request
                .bodyToMono(SubjectDTO.class)
                .flatMap(v->
                        ServerResponse.ok().bodyValue(subjectUseCase.saveSubject(v)));
    }
}
