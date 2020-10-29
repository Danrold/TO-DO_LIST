package com.thebest.todolist.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Класс-сущность позволяющий работать с объектом задача
 * соответствующим таблице task в базе данных
 */

@Entity
@Table(name = "task")
public class Task implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "change_date", nullable = false)
    private Date changeDate;

    @Column(name = "description")
    private String description;

    @Column(name = "is_complete", nullable = false)
    private Boolean isComplete;

    @Column(name = "priority", nullable = false)
    private int priority;

    @Column(name = "list_id", nullable = false)
    private UUID listID;

    public Task() {
        id = UUID.randomUUID();
        createDate = new Date();
        changeDate = new Date();
        isComplete = false;
        priority = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public UUID getListID() {
        return listID;
    }

    public void setListID(UUID listID) {
        this.listID = listID;
    }
}
