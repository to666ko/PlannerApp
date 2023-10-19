package com.plannerapp.model.entity;

import com.plannerapp.model.enums.PriorityName;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "priorities")
public class PriorityEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private PriorityName name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "priority")
    private Set<TaskEntity> tasks;


    public PriorityName getName() {
        return name;
    }

    public PriorityEntity setName(PriorityName name) {
        this.name = name;
        setDescription(name);
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PriorityEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public PriorityEntity setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
        return this;
    }

    private void setDescription(PriorityName name) {
        String description = "";
        switch (name) {
            case URGENT -> description = "An urgent problem that blocks the system use until the issue is resolved";
            case  IMPORTANT -> description = "A core functionality that your product is explicitly supposed to perform is compromised.";
            case LOW -> description = "Should be fixed if time permits but can be postponed.";
        }
        this.description = description;
    }


}
