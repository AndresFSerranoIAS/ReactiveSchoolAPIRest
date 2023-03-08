package com.co.ias.springWebFluxSchool.application.configuration;

import com.co.ias.springWebFluxSchool.domain.model.gateways.IStudentRepository;
import com.co.ias.springWebFluxSchool.domain.model.gateways.ISubjectRepository;
import com.co.ias.springWebFluxSchool.domain.usecase.StudentUseCase;
import com.co.ias.springWebFluxSchool.domain.usecase.SubjectUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {
    @Bean
    public SubjectUseCase subjectUseCase(ISubjectRepository iSubjectRepository){
        return new SubjectUseCase(iSubjectRepository);
    }

    @Bean
    public StudentUseCase studentUseCase(IStudentRepository iStudentRepository,ISubjectRepository iSubjectRepository){
        return new StudentUseCase(iStudentRepository,iSubjectRepository);
    }
}
