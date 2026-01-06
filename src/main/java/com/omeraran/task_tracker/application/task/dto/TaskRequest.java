package com.omeraran.task_tracker.application.task.dto;

import com.omeraran.task_tracker.domain.task.TaskStatus;

public record TaskRequest(Long id, String name, int point, TaskStatus status) {
}
