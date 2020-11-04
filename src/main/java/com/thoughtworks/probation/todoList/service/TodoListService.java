package com.thoughtworks.probation.todoList.service;

import com.thoughtworks.probation.todoList.controller.TodoListController;
import com.thoughtworks.probation.todoList.dto.Event;
import com.thoughtworks.probation.todoList.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;

    @Autowired
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Event> getTodoList() {
        return this.todoListRepository.findAll();
    }
}
