package com.thebest.todolist.control;

import com.thebest.todolist.entity.Task;
import com.thebest.todolist.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * REST-контроллер для работы с сущностью задача.
 */

@RestController
@RequestMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/get")
    @ApiOperation(value = "Отображение задач выбранного списка",
            notes = "Метод должен принимать в качестве параметров условия фильтрации и сортировки")
    public ResponseEntity<List<Task>> getAll(@RequestParam UUID listID)
    {
        return ResponseEntity.ok(taskService.findAll(listID));
    }

    @PostMapping("/add")
    @ApiOperation(value = "Добавление задачи в указанный список",
            notes = "Метод проверяет существование списка с заданным ID, если он существует, то в него добавляется задача с указанным названием и параметрами по умолчанию")
    public ResponseEntity<UUID> addTask(@RequestParam String name, @RequestParam UUID listID)
    {
        if(name.isEmpty()||name==null)
            return new ResponseEntity("incorrect name", HttpStatus.NOT_ACCEPTABLE);

        Task task = new Task();
        task.setName(name);
        task.setListID(listID);
        taskService.saveOne(task);

        return ResponseEntity.ok(task.getId());
    }

    @PutMapping("/edit")
    @ApiOperation(value = "Редактирование задачи",
            notes = "В качестве обязательного параметра принимает ID редактируемой задачи, а так же до трех необязательных параметров:" +
                    "новое название задачи, описание и приоритет в виде числа от 0 до 10")
    public ResponseEntity<UUID> editTask(@RequestParam UUID taskID, String newName, String description, Integer priority)
    {
        return ResponseEntity.ok(taskService.update(taskID, newName, description, priority));
    }

    @PutMapping("/mark")
    @ApiOperation(value = "Отметить задачу выполненной",
            notes = "В данной версии метод инвертирует флаг выполнения дела. То есть возможно так же отметить дело обратно невыполненным")
    public ResponseEntity<UUID> markDone(@RequestParam UUID taskID)
    {
        return ResponseEntity.ok(taskService.markComplete(taskID));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "Удаление задачи",
            notes = "Если задача с данным ID существует, метод производит ее удаление")
    public ResponseEntity delete(@RequestParam UUID taskId)
    {
        if(taskService.delete(taskId))
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }


}
