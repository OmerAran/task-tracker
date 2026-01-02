package com.omeraran.task_tracker;

import lombok.*;

@AllArgsConstructor
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
