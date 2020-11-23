package com.thoughtworks.probation.todoList.service;

import com.thoughtworks.probation.todoList.dto.Event;
import com.thoughtworks.probation.todoList.exception.IdNotMatchedException;
import com.thoughtworks.probation.todoList.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TodoListService {
    private TodoListRepository todoListRepository;

    @Autowired
    public TodoListService(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    public List<Event> getTodoList() {
        return this.todoListRepository.findAllByOrderByTimeStampDesc();
    }

    public int addEventToList(Event event) {
        int preId;
        if (this.todoListRepository.findAll().size() == 0) {
            preId = 1;
        } else {
            preId = this.todoListRepository.findTopByOrderByIdDesc().getId();
        }

        int curId = preId + 1;
        event.setId(curId);

        this.todoListRepository.save(event);
        return curId;
    }

    public void deleteEventById(int id) {
        idCheck(id);
        this.todoListRepository.deleteById(id);
    }

    public void deleteEventByIdS(String checkedIdList) {
        List<Integer> idsList = Arrays.stream(checkedIdList.split(","))
                .map(Integer::valueOf).collect(Collectors.toList());
        idsList.forEach(id -> {
            this.todoListRepository.deleteById(id);
        });
    }

    public void changeEventById(int id, boolean isChangeCheckedStatus, boolean completed, String timeStamp, String detail) {
        idCheck(id);
        Event event = this.todoListRepository.findById(id);

        if (detail != null) {
            event.setDetail(detail);
        }
        if (isChangeCheckedStatus) {
            event.setChecked(!event.isChecked());
        }
        if (timeStamp != null) {
            event.setTimeStamp(timeStamp);
        }
        if (completed) {
            event.setCompleted(completed);
        }
        this.todoListRepository.save(event);
    }

    public void changeAllEventCheckedStatus(boolean isAllChecked) {
        List<Event> lists = this.todoListRepository.findAll();
        lists.forEach(event -> event.setChecked(!isAllChecked));
        this.todoListRepository.saveAll(lists);
    }

    public void idCheck(int id) {
        if (Objects.isNull(this.todoListRepository.findById(id))) {
            throw new IdNotMatchedException(id);
        }
    }
}
