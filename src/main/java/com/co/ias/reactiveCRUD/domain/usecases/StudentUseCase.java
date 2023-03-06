package com.co.ias.reactiveCRUD.domain.usecases;

import com.co.ias.reactiveCRUD.domain.model.gateways.IStudentRepository;
import com.co.ias.reactiveCRUD.domain.model.student.dto.StudentDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StudentUseCase {

    private IStudentRepository iStudentRepository;

    public StudentUseCase(IStudentRepository iStudentRepository) {
        this.iStudentRepository = iStudentRepository;
    }

    public StudentDTO saveStudent(StudentDTO studentDTO){
        return StudentDTO.fromDomain(iStudentRepository.saveStudent(StudentDTO.toDomain(studentDTO)));
    }
    public List<StudentDTO> getStudents(){
        return iStudentRepository
                .getStudents()
                .stream()
                .map(StudentDTO::fromDomain)
                .collect(Collectors.toList());
    }
}
