package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.subject;

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

    @Value("${PATH_BASE}")
    private String PATH_BASE;

    @Value("${PATH_SUBJECTS}")
    private String PATH_SUBJECTS;

    @Bean
    public RouterFunction<ServerResponse> subjectsEndPoints(SubjectHandler subjectHandler){
        return route()
                .POST(PATH_BASE.concat(PATH_SUBJECTS).concat("/new"), accept(MediaType.APPLICATION_JSON), subjectHandler::saveSubject)
                .GET(PATH_BASE.concat(PATH_SUBJECTS), accept(MediaType.APPLICATION_JSON), subjectHandler::getSubjects)
                .GET(PATH_BASE.concat(PATH_SUBJECTS).concat("/{id}"), accept(MediaType.APPLICATION_JSON), subjectHandler::getSubjectById)
                .PUT(PATH_BASE.concat(PATH_SUBJECTS).concat("/{id}"), accept(MediaType.APPLICATION_JSON), subjectHandler::updateSubject)
                .build();
    }

}