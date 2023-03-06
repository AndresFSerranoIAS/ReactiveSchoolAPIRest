package com.co.ias.reactiveCRUD.infrastructure.entrypoint;

import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.student.StudentHandler;
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
    @Value("{PATH_BASE}")
    private String PATH_BASE;
    @Value("{PATH_STUDENTS}")
    private String PATH_STUDENTS;
    @Bean
    public RouterFunction<ServerResponse> studentEndPoints(StudentHandler studentHandler){
        return route().POST("/api".concat("/students").concat("/new"),accept(MediaType.APPLICATION_JSON),studentHandler::saveStudent)
                .build();
    }
}
