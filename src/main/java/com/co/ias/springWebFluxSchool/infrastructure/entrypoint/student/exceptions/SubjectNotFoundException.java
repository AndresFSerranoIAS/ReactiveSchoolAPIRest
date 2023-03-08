package com.co.ias.springWebFluxSchool.infrastructure.entrypoint.student.exceptions;

public class SubjectNotFoundException extends NullPointerException{
   public SubjectNotFoundException(String message){
       super(message);
   }


}
