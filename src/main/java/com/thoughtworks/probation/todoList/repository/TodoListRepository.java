package com.thoughtworks.probation.todoList.repository;


import com.thoughtworks.probation.todoList.dto.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TodoListRepository extends CrudRepository<Event, Integer> {
    List<Event> findAll();
    Event findTopByOrderByIdDesc();
}
