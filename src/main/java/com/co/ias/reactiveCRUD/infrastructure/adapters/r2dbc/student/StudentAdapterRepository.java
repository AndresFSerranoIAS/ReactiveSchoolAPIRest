package com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.student;

import com.co.ias.reactiveCRUD.domain.model.gateways.IStudentRepository;
import com.co.ias.reactiveCRUD.domain.model.student.Student;
import com.co.ias.reactiveCRUD.infrastructure.adapters.r2dbc.entity.StudentDBO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class StudentAdapterRepository implements IStudentRepository {
    public final IStudentRepositoryAdapter iStudentRepositoryAdapter;

    public StudentAdapterRepository(IStudentRepositoryAdapter iStudentRepositoryAdapter) {
        this.iStudentRepositoryAdapter = iStudentRepositoryAdapter;
    }

    @Override
    public Student saveStudent(Student student) {
        Mono<StudentDBO> studentDBOMono = Mono
                .just(StudentDBO
                        .fromDomain(student));

        Mono<StudentDBO> savedStudentDBOMono = studentDBOMono
                .flatMap(iStudentRepositoryAdapter::save);
        StudentDBO studentDBO = new StudentDBO();
        savedStudentDBOMono
                .subscribe(
                        x->{
                            System.out.println(x.getStudentName());
                            studentDBO.setStudentId(x.getStudentId());
                            studentDBO.setStudentName(x.getStudentName());
                            studentDBO.setStudentEmail(x.getStudentEmail());
                            studentDBO.setSubjectId(x.getSubjectId());
                            System.out.println(studentDBO.getSubjectId());});
        return StudentDBO
                .toDomain(studentDBO);
    }
    @Override
    public List<Student> getStudents() {
        return null;
    }
}
