package com.omeraran.task_tracker.domain.task;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder(builderMethodName = "builder")
public class Task {
    private Long id;
    private Long userId;
    private String name;
    private int point;
    private TaskStatus status;
}
