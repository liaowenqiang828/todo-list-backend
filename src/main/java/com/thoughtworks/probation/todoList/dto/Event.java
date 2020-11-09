package com.thoughtworks.probation.todoList.dto;

import com.thoughtworks.probation.todoList.constant.ErrorConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "todo_list")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull( message = ErrorConstant.EVENT_DETAIL_NULL_ERROR)
    private String detail;
    private boolean completed = false;
    private String timeStamp;
}
