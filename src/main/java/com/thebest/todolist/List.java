package com.thebest.todolist;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // TODO: генерация не нужна. нужно присвоение в конструкторе,
    // либо при создании, UUID гарантирует что пересечения с другим ID не будет
    @Column(name = "id")
    UUID id;

    @Column(name = "createdate", nullable = false) // TODO: поля таблиц должны быть в формате XXX_YYY
    private Date createDate;

    @Column(name = "changedate", nullable = false) // TODO: поля таблиц должны быть в формате XXX_YYY
    private Date changeDate;

    @Column(name = "listname", nullable = false, unique = true) // TODO: поля таблиц должны быть в формате XXX_YYY, listname->name? т.к. и так понятно что .то имя листа,а так короче идентификатор
    private String listName; // TODO:  listname->name? т.к. и так понятно что .то имя листа,а так короче идентификатор

    public List() { // TODO: если нет конструкторов то конструктор без параметров в java классе есть по умолчанию
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

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
