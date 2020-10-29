package com.thebest.todolist.service;

import com.thebest.todolist.entity.Task;

import java.util.*;

public interface TaskService {

    List<Task> findAll(UUID listID);

    UUID saveOne(Task task);

    UUID update(Task task);

    UUID markComplete(UUID taskId);

    Boolean delete(UUID taskID);

}
