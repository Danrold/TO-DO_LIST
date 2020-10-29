package com.thebest.todolist.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Java-doc к классу и свойствам
 */
@Entity
@Table(name = "list")
public class List implements Serializable {

    @Id
    @Column(name = "id")
    UUID id;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "change_date", nullable = false)
    private Date changeDate;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public List() {
        id = UUID.randomUUID();
        createDate = new Date();
        changeDate = new Date();
    }

    // TODO: все эти геттеры и сеттеры заменяются на аннотации @Getter/@Setter от lombok/ укоротит количество кода
    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public void setName(String listName) {
        this.name = listName;
    }
}
