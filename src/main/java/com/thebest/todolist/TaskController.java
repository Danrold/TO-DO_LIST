package com.thebest.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public void getAll()
    {

    }

    @PostMapping("/")
    public void addTask()
    {

    }

    @PutMapping("/edit")
    public void editTask()
    {

    }

    @PutMapping("/mark")
    public void markDone()
    {

    }

    @DeleteMapping("/")
    public void delete()
    {

    }


}
