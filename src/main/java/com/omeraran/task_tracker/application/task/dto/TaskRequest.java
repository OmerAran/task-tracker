package com.omeraran.task_tracker.application.task.dto;

import com.omeraran.task_tracker.domain.task.TaskStatus;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record TaskRequest(Long id, @Size(min = 3, max = 20, message = "Size should be at least 3 and max 20 characters.") String name,
                          @Max(value = 10, message = "Code must be less than 10") @Min(value = 0, message = "Code must be greater than 0") int point,
                          TaskStatus status) {
}
