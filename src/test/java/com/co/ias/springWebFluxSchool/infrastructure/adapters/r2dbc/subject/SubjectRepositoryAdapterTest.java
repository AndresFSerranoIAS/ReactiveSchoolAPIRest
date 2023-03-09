package com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.subject;

import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.model.subject.Subject;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectId;
import com.co.ias.springWebFluxSchool.domain.model.subject.SubjectName;
import com.co.ias.springWebFluxSchool.infrastructure.adapters.r2dbc.entity.SubjectDBO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SubjectRepositoryAdapterTest {
    @MockBean
    ISubjectRepository iSubjectRepository;
    @Autowired
    ISubjectAdapterRepository iSubjectAdapterRepository;
    @Test
    void saveSubject() {
        SubjectDBO subjectDBO = new SubjectDBO(1L,"Programación");
        Mono<SubjectDBO> savedSubjectDBO = iSubjectAdapterRepository.deleteAll()
                .then(iSubjectAdapterRepository.save(subjectDBO))
                .then(iSubjectAdapterRepository.findById(1L));

        StepVerifier
                .create(savedSubjectDBO)
                .expectNextMatches(
                        subject -> {
                            assertEquals(subject.getSubjectId(),1L);
                            assertEquals(subject.getSubjectName(),"Programación");
                            return true;
                        })
                .verifyComplete();
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