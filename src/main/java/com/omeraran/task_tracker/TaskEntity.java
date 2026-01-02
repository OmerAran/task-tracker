package com.omeraran.task_tracker;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String name;
    private int point;
    private TaskStatus status;


    public Task toModel() {
        return Task.builder()
                .id(id)
                .userId(userId)
                .name(this.name)
                .point(this.point)
                .status(this.status)
                .build();
    }
}
