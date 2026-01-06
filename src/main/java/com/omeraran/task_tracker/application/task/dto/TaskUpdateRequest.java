package com.omeraran.task_tracker.application.task.dto;

import com.omeraran.task_tracker.domain.task.TaskStatus;

public record TaskUpdateRequest(TaskStatus status) {
}
