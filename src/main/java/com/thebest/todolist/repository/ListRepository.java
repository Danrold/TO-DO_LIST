package com.thebest.todolist.repository;

import com.thebest.todolist.entity.List;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Репозиторий с помощью которого осуществляется операции доступа к таблице list в базе данных
 */
public interface ListRepository extends JpaRepository<List, UUID> {

}
