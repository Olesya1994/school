package ru.hogwarts.school.service;

public class IdExistsException extends RuntimeException{
    public IdExistsException(String message) {
        super(message);
    }
}
