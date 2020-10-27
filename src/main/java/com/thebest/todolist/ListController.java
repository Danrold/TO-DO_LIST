package com.thebest.todolist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public ResponseEntity<List> add(@RequestParam String listName) {
        if(listName.isEmpty() || listName == null) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }
        List list = new List();
        list.setListName(listName);
        list.setCreateDate(new Date());
        list.setChangeDate(new Date());

        return ResponseEntity.ok(listRepository.save(list));
    }

}
