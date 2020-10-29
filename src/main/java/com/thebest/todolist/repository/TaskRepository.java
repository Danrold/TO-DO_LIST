package com.thebest.todolist.repository;

import com.thebest.todolist.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByListID(UUID listID);
}
