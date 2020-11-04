package com.thebest.todolist.control;

import com.thebest.todolist.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST-контроллер для работы с сущностью список списков.
 */
@RestController
@RequestMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
public class ListController {

    private final ListService listService;

    @Autowired
    public ListController(ListService listService) {
        this.listService = listService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Отображение доступных списков",
            notes = "Метод не принимает никаких параметров и возвращает все существующие списки")
    public ResponseEntity<java.util.List<com.thebest.todolist.entity.List>> allList() {

        return ResponseEntity.ok(listService.findAll());
    }
    // TODO: не хватет сортировки, фильтрации и пагинации

    @GetMapping("/page")
    @ApiOperation(value = "Построчное отображение доступных списков",
            notes = "Метод принимает в качестве параметров номер страницы, количество отображаемых списокв, название поля для сортировки")
    public ResponseEntity<Page<com.thebest.todolist.entity.List>> viewPage(Integer pageNumber, Integer size, String sortBy){
        return ResponseEntity.ok(listService.getPage(pageNumber, size, sortBy));
    }

    @PostMapping("/")
    @ApiOperation(value = "Добавление нового списка",
            notes = "В качестве параметра метод принимает имя создаваемого списком. Дата создания и изменения устанавливаются текущие.")
    public ResponseEntity<UUID> add(@RequestParam String listName) {
        if(listName.isEmpty()) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }
        com.thebest.todolist.entity.List list = new com.thebest.todolist.entity.List();
        list.setName(listName);

        return ResponseEntity.ok(listService.saveOne(list));
    }

    @PutMapping("/")
    @ApiOperation(value = "Переименование существующего списка",
            notes = "Метод принимает в качестве параметров ID изменяемого списка и новое имя")
    public ResponseEntity<UUID> updateList(@RequestParam UUID id, @RequestParam String newName)
    {
        if(newName.isEmpty()) {
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(listService.update(newName, id));
    }

    @DeleteMapping("/")
    @ApiOperation(value = "Удаление указанного списка",
            notes = "Метод удаляет список по его ID")
    public ResponseEntity deleteList(@RequestParam UUID id)
    {
        if(listService.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
