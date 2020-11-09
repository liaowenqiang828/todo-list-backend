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

    @DeleteMapping("/event/{id}")
    public void deleteEventById(@PathVariable int id) {
        this.todoListService.deleteEventById(id);
    }

    @PatchMapping("/event")
    public void changeEventStatusById(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "completed", required = false) boolean completed,
            @RequestParam(value = "detail", required = false) String detail) {
        this.todoListService.changeEventStatusById(id, completed, detail);
    }

}
