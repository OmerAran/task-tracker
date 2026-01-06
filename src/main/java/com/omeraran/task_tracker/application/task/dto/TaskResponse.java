package com.omeraran.task_tracker.application.task.dto;

import com.omeraran.task_tracker.domain.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class TaskResponse {
    private Long id;
    private Long userId;
    private String name;
    private int point;
    private TaskStatus status;
}
