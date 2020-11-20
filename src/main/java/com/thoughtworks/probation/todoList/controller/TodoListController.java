package com.thoughtworks.probation.todoList.controller;

import com.thoughtworks.probation.todoList.dto.Event;
import com.thoughtworks.probation.todoList.service.TodoListService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Validated
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
    public int addEventToList(@RequestBody @Valid Event event) {
        return this.todoListService.addEventToList(event);
    }

    @DeleteMapping("/event/{id}")
    public void deleteEventById(@PathVariable int id) {
        this.todoListService.deleteEventById(id);
    }

    @PatchMapping("/event")
    public void changeEventById(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "timeStamp", required = false) String timeStamp,
            @RequestParam(value = "completed", required = false) boolean completed,
            @RequestParam(value = "detail", required = false) String detail,
            @RequestParam(value = "isChangeCheckedStatus", required = false) boolean isChangeCheckedStatus) {
        this.todoListService.changeEventById(id, isChangeCheckedStatus,completed, timeStamp, detail);
    }

}
