package com.thebest.todolist.service;

import com.google.common.collect.Lists;
import com.thebest.todolist.entity.List;
import com.thebest.todolist.repository.ListRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Имплементация интерфейса ListService, описывающая логику работы с listRepository
 */

@Service
public class ListServiceImpl implements ListService{

    private final ListRepository listRepository;
    private final int defaultSize = 2;

    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public java.util.List<List> findAll() {
        return listRepository.findAll();
    }

    @Override
    public Page<List> getPage(Integer pageNumber, Integer size, String sortBy) {
        if(pageNumber == null)
            pageNumber = 0;
        if (size == null)
            size = defaultSize;

        Pageable page;
        java.util.List<Field> columnName = Lists.newArrayList(List.class.getDeclaredFields());

        if(sortBy != null){
            for (Field item:columnName) {
                if(sortBy.equals(item.getName())){
                    page = PageRequest.of(pageNumber, size, Sort.by(sortBy));
                    return listRepository.findAll(page);
                }
            }
        }

        page = PageRequest.of(pageNumber, size);

        return listRepository.findAll(page);
    }

    @Override
    public UUID saveOne(List list) {
        listRepository.save(list);
        return list.getId();
    }

    @Override
    public UUID update(String name, UUID id) {

        List list = listRepository.findById(id).orElseThrow(()->new EntityNotFoundException());
        list.setName(name);
        list.setChangeDate(new Date());
        listRepository.save(list);

        return list.getId();
    }

    @Override
    public Boolean delete(UUID id) {

        if (listRepository.findById(id).isPresent()){
            listRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
