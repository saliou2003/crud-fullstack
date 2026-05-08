package com.example.demo;

public class PersonNotFoundExcepttion extends RuntimeException{

    public PersonNotFoundExcepttion(String message){
        super(message);
    }
}
