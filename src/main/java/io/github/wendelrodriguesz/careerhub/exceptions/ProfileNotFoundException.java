package io.github.wendelrodriguesz.careerhub.exceptions;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException(String message){
        super(message);
    }
}
