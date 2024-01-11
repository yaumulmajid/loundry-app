package com.codeapps.loundry.exceptions;

public class NotFoundException extends CustomException{
    public NotFoundException(){
        this("Data Not Found");
    }
    public NotFoundException(String message) {
        super(message);
    }
}
