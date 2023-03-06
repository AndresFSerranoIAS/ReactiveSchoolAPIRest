package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.student;

import com.co.ias.reactiveCRUD.domain.model.student.dto.StudentDTO;
import com.co.ias.reactiveCRUD.domain.usecases.StudentUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class StudentHandler {

    private StudentUseCase studentUseCase;
    public StudentHandler(StudentUseCase studentUseCase){
        this.studentUseCase=studentUseCase;
    }
    public Mono<ServerResponse> saveStudent(ServerRequest request){
        return request
                .bodyToMono(StudentDTO.class)
                .flatMap(v ->
                        ServerResponse.ok().bodyValue(studentUseCase.saveStudent(v)));
    }

}
