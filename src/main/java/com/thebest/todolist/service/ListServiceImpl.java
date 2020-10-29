package com.thebest.todolist.service;

import com.thebest.todolist.entity.List;
import com.thebest.todolist.repository.ListRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ListServiceImpl implements ListService{

    private final ListRepository listRepository;

    public ListServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public java.util.List<List> findAll() {
        return listRepository.findAll();
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
