package com.thebest.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListController {

    private final ListRepository listRepository;

    @Autowired
    public ListController(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @GetMapping("/getlist")
    public ResponseEntity<java.util.List<List>> allList() {
        return ResponseEntity.ok(listRepository.findAll());
    }


    @PostMapping("/list")
    public ResponseEntity<UUID> add(@RequestParam String listName) {
        if(listName.isEmpty() || listName == null) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }
        List list = new List();
        list.setListName(listName);
        list.setCreateDate(new Date());
        list.setChangeDate(new Date());

        listRepository.save(list);

        return ResponseEntity.ok(list.getId());
    }

    @PutMapping("/list")
    public ResponseEntity<List> updateList(@RequestParam UUID id, String newName)
    {
        if(newName.isEmpty() || newName == null) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }
        List list = listRepository.getOne(id);
        if (list == null){
            return new ResponseEntity("incorrect id", HttpStatus.NOT_FOUND);
        }

        list.setListName(newName);
        list.setChangeDate(new Date());

        return ResponseEntity.ok(listRepository.save(list));
    }

    @DeleteMapping("/list")
    public ResponseEntity deleteList(@RequestParam UUID id)
    {
        List list = listRepository.getOne(id);
        if (list == null){
            return new ResponseEntity("incorrect id", HttpStatus.NOT_FOUND);
        }
        listRepository.delete(list);

        return new ResponseEntity(HttpStatus.OK);
    }

}
