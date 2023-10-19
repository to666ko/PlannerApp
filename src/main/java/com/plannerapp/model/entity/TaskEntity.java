package com.plannerapp.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="tasks")
public class TaskEntity extends BaseEntity{

    @Length(min = 2, max = 50)
    @Column(nullable = false)
    private String description;

    @Future
    @Column(nullable = false)
    private LocalDate dueDate;

    @ManyToOne
    @NotNull
    private PriorityEntity priority;


    @ManyToOne
    private UserEntity assignee;

    public String getDescription() {
        return description;
    }

    public TaskEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskEntity setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }

    public PriorityEntity getPriority() {
        return priority;
    }

    public TaskEntity setPriority(PriorityEntity priority) {
        this.priority = priority;
        return this;
    }

    public UserEntity getAssignee() {
        return assignee;
    }

    public TaskEntity setAssignee(UserEntity assignee) {
        this.assignee = assignee;
        return this;
    }
}
