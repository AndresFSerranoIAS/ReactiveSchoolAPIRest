package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.subject;

import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectName;
import com.co.ias.springWebFluxSchool.domain.usecase.SubjectUseCase;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@WebFluxTest
@Import({SubjectRoutes.class,SubjectHandler.class})
class SubjectHandlerTest {

    @MockBean
    private SubjectUseCase subjectUseCase;
    @Autowired
    private WebTestClient testClient;

    @Test
    void saveSubjectTest() {
        Subject subject = new Subject(new SubjectId(1L),new SubjectName("Programación"));
        when(subjectUseCase.saveSubject(any())).thenReturn(Mono.just(subject));

        testClient.post()
                .uri("/api/subjects/new")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(subject), Subject.class)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(String.class)
                .value(Matchers.equalTo("La materia " + subject.getSubjectName().getValue() + " se ha agregado satisfactoriamente a la base de datos."));
    }

    @Test
    void getSubjects() {
    }

    @Test
    void getSubjectById() {
    }

    @Test
    void updateSubject() {
    }

    @Test
    void deleteSubject() {
    }
}