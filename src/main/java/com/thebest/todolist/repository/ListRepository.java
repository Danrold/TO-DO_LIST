package com.thebest.todolist.repository;

import com.thebest.todolist.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Java-док
 */
public interface ListRepository extends JpaRepository<List, UUID> {

}
