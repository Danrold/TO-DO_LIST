package com.thebest.todolist.service;

import com.thebest.todolist.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

/**
 * Интерфес, описывающий необходимые методы для работы с объектом task в базе данных
 */
public interface TaskService {

    List<Task> findAll(UUID listID);

    Page<Task> getPage(UUID listID, Integer pageNumber, Integer size, String sortBy);

    UUID saveOne(Task task);

    UUID update(UUID taskId, String newName, String description, Integer priority);

    UUID markComplete(UUID taskId);

    Boolean delete(UUID taskID);

}
