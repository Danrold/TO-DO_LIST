package com.thebest.todolist.service;

import com.thebest.todolist.entity.Task;
import com.thebest.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

/**
 * Имплементация интерфейса TaskService, описывающая логику работы с taskRepository
 */
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
    public UUID update(UUID taskId, String newName, String description, Integer priority) {
        Task task = taskRepository.findById(taskId).orElseThrow(()->new EntityNotFoundException());
        if(newName!=null) task.setName(newName);
        if(description!=null) task.setDescription(description);
        if(priority!=null && priority<=10 && priority>=0) task.setPriority(priority);
        task.setChangeDate(new Date());
        taskRepository.save(task);
        return task.getId();
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
