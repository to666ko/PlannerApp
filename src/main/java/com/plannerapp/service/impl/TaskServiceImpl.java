package com.plannerapp.service.impl;

import com.plannerapp.model.dto.task.TaskAddBindingModel;
import com.plannerapp.model.dto.task.TaskDTO;
import com.plannerapp.model.dto.task.TaskHomeViewModel;
import com.plannerapp.model.entity.PriorityEntity;
import com.plannerapp.model.entity.TaskEntity;
import com.plannerapp.model.entity.UserEntity;
import com.plannerapp.repo.PriorityRepository;
import com.plannerapp.repo.TaskRepository;
import com.plannerapp.repo.UserRepository;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final PriorityRepository priorityRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PriorityRepository priorityRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.priorityRepository = priorityRepository;
        this.userRepository = userRepository;
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
            TaskEntity task = optionalTask.get();

            if (username == null) {
                task.setAssignee(null);
            } else {
                UserEntity user = userRepository.findByUsername(username);
                task.setAssignee(user);
            }

            taskRepository.save(task);

        }

    }

    @Override
    public TaskHomeViewModel getHomeViewData(String username) {
        UserEntity user = userRepository.findByUsername(username);

        List<TaskDTO> assignedTasks = taskRepository
                .findByAssignee(user)
                .stream()
                .map(TaskDTO::createFromTask)
                .toList();

        List<TaskDTO> availableTasks = taskRepository
                .getAllAvailable()
                .stream()
                .map(TaskDTO::createFromTask)
                .toList();

        return new TaskHomeViewModel(assignedTasks, availableTasks);



    }


}
