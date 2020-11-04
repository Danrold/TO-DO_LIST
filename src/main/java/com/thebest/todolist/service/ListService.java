package com.thebest.todolist.service;

import org.springframework.data.domain.*;

import java.util.*;

/**
 * Интерфес, описывающий необходимые методы для работы с объектом list в базе данных
 */
public interface ListService {

    List<com.thebest.todolist.entity.List> findAll();

    Page<com.thebest.todolist.entity.List> getPage(Integer pageNumber, Integer size, String sortBy);

    UUID saveOne(com.thebest.todolist.entity.List list);

    UUID update(String name, UUID id);

    Boolean delete(UUID id);

}
