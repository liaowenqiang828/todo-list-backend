package com.thoughtworks.probation.todoList.service;

import com.thoughtworks.probation.todoList.dto.Event;
import com.thoughtworks.probation.todoList.exception.IdNotMatchedException;
import com.thoughtworks.probation.todoList.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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

    public int addEventToList(Event event) {
        int preId = this.todoListRepository.findTopByOrderByIdDesc().getId();
        int curId = preId + 1;
        event.setId(curId);

        this.todoListRepository.save(event);
        return curId;
    }

    public void deleteEventById(int id) {
        idCheck(id);
        this.todoListRepository.deleteById(id);
    }

    public void changeEventStatusById(int id, boolean completed, String detail) {
        idCheck(id);
        Event event = this.todoListRepository.findById(id);

        if (detail != null) {
            event.setDetail(detail);
        }
        event.setCompleted(completed);
        this.todoListRepository.save(event);
    }

    public void idCheck(int id) {
        if (Objects.isNull(this.todoListRepository.findById(id))) {
            throw new IdNotMatchedException(id);
        }
    }
}
