package com.plannerapp.service;


import com.plannerapp.model.dto.task.TaskAddBindingModel;

public interface TaskService {

    void add(TaskAddBindingModel taskAddBindingModel);

    void remove(Long id);

    void assign(Long id, String username);
}
