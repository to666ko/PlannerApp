package com.plannerapp.controller;

import com.plannerapp.model.dto.task.TaskAddBindingModel;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks/add")
    public ModelAndView add(@ModelAttribute("taskAddBindingModel") TaskAddBindingModel taskAddBindingModel) {
        return new ModelAndView("task-add");
    }

    @PostMapping("/tasks/add")
    public ModelAndView add(
            @ModelAttribute("taskAddBindingModel") @Valid TaskAddBindingModel taskAddBindingModel,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("task-add");
        }

        taskService.add(taskAddBindingModel);

        return new ModelAndView("redirect:/home");


    }

}
