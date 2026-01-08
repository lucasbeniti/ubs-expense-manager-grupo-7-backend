package com.empresa.projeto.gestao_ubs.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    // Make more exceptions

    public ResourceNotFoundException(String message){
        super(message);
    }
}
