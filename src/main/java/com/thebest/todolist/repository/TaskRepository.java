package com.thebest.todolist.repository;

import com.thebest.todolist.entity.Task;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

/**
 * Репозиторий с помощью которого осуществляется операции доступа к таблице task в базе данных
 */

public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findByListID(UUID listID);
    Page<Task> findByListID(UUID listID, Pageable pageable);
}
