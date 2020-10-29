package com.thebest.todolist.control;

import com.thebest.todolist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Java-док
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    // TODO: ресты не долждны ничего знать об обьектной модели, они оперируют DTO (можно в гугле спросить DTO зачем нужны)
    //  также они не должны ничего знать о репозиториях, все действия долны быть вынесены в сервисы через интерфейсы,
    //  чтоб мы могли подменить реализацию

    @GetMapping("/getlist")
    public ResponseEntity<java.util.List<com.thebest.todolist.entity.List>> allList() {
        return ResponseEntity.ok(listService.findAll());
    }
    // TODO: не хватет сортировки, фильтрации и пагинации


    @PostMapping("/list")
    public ResponseEntity<UUID> add(@RequestParam String listName) {
        if(listName.isEmpty() || listName == null) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }
        com.thebest.todolist.entity.List list = new com.thebest.todolist.entity.List();
        list.setName(listName);

        return ResponseEntity.ok(listService.saveOne(list));
    }

    @PutMapping("/list")
    public ResponseEntity<UUID> updateList(@RequestParam UUID id, String newName)
    {
        if(newName.isEmpty() || newName == null) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(listService.update(newName, id));
    }

    @DeleteMapping("/list")
    public ResponseEntity deleteList(@RequestParam UUID id)
    {
        if(listService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
