package com.misaelpereiradev.persistence_playground.persons.domain.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}
