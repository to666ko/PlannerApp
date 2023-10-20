package com.plannerapp.model.dto.task;

import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.model.enums.PriorityName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class TaskDTO {

    private Long id;

    private String description;

//    @DateTimeFormat(pattern = "yyyy-qMM-dd")
    private LocalDate dueDate;

    private PriorityName priority;

    public Long getId() {
        return id;
    }

    public TaskDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public TaskDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskDTO setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PriorityName getPriority() {
        return priority;
    }

    public TaskDTO setPriority(PriorityName priority) {
        this.priority = priority;
        return this;
    }


    public static TaskDTO createFromTask(TaskEntity task){
        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setPriority(task.getPriority().getName());
        taskDTO.setDueDate(task.getDueDate());

        return taskDTO;
    }
}
