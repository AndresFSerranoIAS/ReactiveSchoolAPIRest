package com.co.ias.reactiveCRUD.infrastructure.entrypoint;

import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.student.StudentHandler;
import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.subject.SubjectHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SubjectRoutes {

    @Value("{PATH_BASE}")
    private String PATH_BASE;
    @Value("{PATH_SUBJECTS}")
    private String PATH_SUBJECTS;
    @Bean
    public RouterFunction<ServerResponse> subjectsEndPoints(SubjectHandler subjectHandler){
        return route().POST("/api".concat("/subjects").concat("/new"),accept(MediaType.APPLICATION_JSON),subjectHandler::saveSubject)
                .build();
    }
}
