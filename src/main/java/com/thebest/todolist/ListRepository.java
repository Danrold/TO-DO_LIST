package com.thebest.todolist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ListRepository extends JpaRepository<List, UUID> {

}
