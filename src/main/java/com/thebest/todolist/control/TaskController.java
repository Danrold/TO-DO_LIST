package com.thebest.todolist.control;

import com.thebest.todolist.entity.Task;
import com.thebest.todolist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<Task>> getAll(@RequestParam UUID listID)
    {
        return ResponseEntity.ok(taskService.findAll(listID));
    }

    @PostMapping("/add")
    public ResponseEntity<UUID> addTask(@RequestParam String name, @RequestParam UUID listID)
    {
        if(name.isEmpty()||name==null)
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);

        Task task = new Task();
        task.setName(name);
        task.setListID(listID);
        taskService.saveOne(task);

        return ResponseEntity.ok(task.getId());
    }

    @PutMapping("/edit")
    public void editTask()
    {

    }

    @PutMapping("/mark")
    public ResponseEntity<UUID> markDone(@RequestParam UUID taskID)
    {
        return ResponseEntity.ok(taskService.markComplete(taskID));
    }

    @DeleteMapping("/")
    public ResponseEntity delete(@RequestParam UUID taskId)
    {
        if(taskService.delete(taskId))
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


}
