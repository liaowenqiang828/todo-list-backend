package com.thoughtworks.probation.todoList.controller;

import com.thoughtworks.probation.todoList.dto.Event;
import com.thoughtworks.probation.todoList.service.TodoListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class TodoListController {
    private final TodoListService todoListService;
    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping("/lists")
    public List<Event> getTodoLists() {
        return this.todoListService.getTodoList();
    }

    @PostMapping("/event")
    public int addEventToList(@RequestBody Event event) {
        return this.todoListService.addEventToList(event);
    }
}
