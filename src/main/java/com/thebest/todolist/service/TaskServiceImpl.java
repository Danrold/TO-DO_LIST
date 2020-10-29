package com.thebest.todolist.service;

import com.thebest.todolist.entity.Task;
import com.thebest.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll(UUID listID) {
        return taskRepository.findByListID(listID);
    }

    @Override
    public UUID saveOne(Task task) {
        taskRepository.save(task);
        return task.getId();
    }

    @Override
    public UUID update(Task task) {
        return null;
    }

    @Override
    public UUID markComplete(UUID taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->new EntityNotFoundException());
        task.setComplete(!task.getComplete());
        taskRepository.save(task);
        return task.getId();
    }

    @Override
    public Boolean delete(UUID taskID) {
        if (taskRepository.findById(taskID).isPresent()) {
            taskRepository.deleteById(taskID);
            return true;
        }
        return false;
    }

}
