package com.codeapps.loundry.exceptions;

public class RegisterFailedException extends CustomException{
    public RegisterFailedException(){
        this("Data Not Found");
    }
    public RegisterFailedException(String message) {
        super(message);
    }
}
