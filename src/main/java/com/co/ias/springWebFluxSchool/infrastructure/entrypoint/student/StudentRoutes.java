package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.student;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class StudentRoutes {

    @Value("${PATH_BASE}")
    private String PATH_BASE;

    @Value("${PATH_STUDENTS}")
    private String PATH_STUDENTS;

    @Bean
    public RouterFunction<ServerResponse> studentsEndPoints(StudentHandler studentHandler){
        return route()
                .POST(PATH_BASE.concat(PATH_STUDENTS).concat("/new"),accept(MediaType.APPLICATION_JSON),studentHandler::saveStudent)
                .GET(PATH_BASE.concat(PATH_STUDENTS).concat("/{id}"),accept(MediaType.APPLICATION_JSON),studentHandler::getStudentById)
                .GET(PATH_BASE.concat(PATH_STUDENTS).concat("/"),accept(MediaType.APPLICATION_JSON),studentHandler::getAllStudents)
                .PUT(PATH_BASE.concat(PATH_STUDENTS).concat("/{id}"),accept(MediaType.APPLICATION_JSON),studentHandler::updateStudent)
                .build();
    }
}
