package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.student.exceptions;

public class StudentNotFoundException extends NullPointerException{

    public StudentNotFoundException(String message){
        super(message);
    }
}
