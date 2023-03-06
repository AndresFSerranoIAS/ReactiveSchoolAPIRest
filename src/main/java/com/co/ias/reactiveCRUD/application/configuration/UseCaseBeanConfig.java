package com.co.ias.reactiveCRUD.application.configuration;

import com.co.ias.reactiveCRUD.domain.model.gateways.IStudentRepository;
import com.co.ias.reactiveCRUD.domain.model.gateways.ISubjectRepository;
import com.co.ias.reactiveCRUD.domain.usecases.StudentUseCase;
import com.co.ias.reactiveCRUD.domain.usecases.SubjectUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    public StudentUseCase studentUseCase(IStudentRepository iStudentRepository){
        return new StudentUseCase(iStudentRepository);
    }
    @Bean
    public SubjectUseCase subjectUseCase(ISubjectRepository iSubjectRepository){
        return new SubjectUseCase(iSubjectRepository);
    }
}
