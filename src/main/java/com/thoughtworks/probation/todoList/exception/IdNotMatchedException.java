package com.thoughtworks.probation.todoList.exception;

public class IdNotMatchedException extends RuntimeException {
    public IdNotMatchedException(int id) {
        super("Id: " + id + " not exist");
    }
}
