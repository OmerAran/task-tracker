package com.omeraran.task_tracker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Validator {

    private final TaskRepository taskRepository;
}
