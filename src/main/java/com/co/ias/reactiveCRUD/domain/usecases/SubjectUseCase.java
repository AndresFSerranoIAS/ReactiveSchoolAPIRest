package com.co.ias.reactiveCRUD.domain.usecases;

import com.co.ias.reactiveCRUD.domain.model.gateways.ISubjectRepository;
import com.co.ias.reactiveCRUD.domain.model.subject.Subject;
import com.co.ias.reactiveCRUD.domain.model.subject.dto.SubjectDTO;

import java.util.List;
import java.util.stream.Collectors;

public class SubjectUseCase {

    private ISubjectRepository iSubjectRepository;

    public SubjectUseCase(ISubjectRepository iSubjectRepository) {
        this.iSubjectRepository = iSubjectRepository;
    }

    public SubjectDTO saveSubject(SubjectDTO subjectDTO){
        return SubjectDTO.fromDomain(iSubjectRepository.saveSubject(SubjectDTO.toDomain(subjectDTO)));
    }

    public List<SubjectDTO> getSubjects(){
        return iSubjectRepository
                .getSubjects()
                .stream()
                .map(SubjectDTO::fromDomain)
                .collect(Collectors.toList());
    }

}
