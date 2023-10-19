package com.plannerapp.service.impl;

import com.plannerapp.model.dto.task.TaskAddBindingModel;
import com.plannerapp.model.entity.PriorityEntity;
import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
    }

    @Override
    public void add(TaskAddBindingModel taskAddBindingModel) {
        PriorityEntity priority = priorityRepository.findByName(taskAddBindingModel.getPriority());

        if (priority != null) {
            TaskEntity task = new TaskEntity();
            task.setDescription(taskAddBindingModel.getDescription());
            task.setDueDate(taskAddBindingModel.getDueDate());
            task.setPriority(priority);

            taskRepository.save(task);
        }


    }

    @Override
    public void remove(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void assign(Long id, String username) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {

            if (username == null) {

            }
        }
    }
}
