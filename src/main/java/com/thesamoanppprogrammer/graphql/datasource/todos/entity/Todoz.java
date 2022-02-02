package com.thesamoanppprogrammer.graphql.datasource.todos.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "todoz")
public class Todoz {

    @Id
    private UUID id;

//    @CreationTimestamp
//    private LocalDateTime creationTimestamp;

    private String todoTitle;
    private Boolean completed;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTodoTitle() {
        return todoTitle;
    }

    public void setTodoTitle(String todoTitle) {
        this.todoTitle = todoTitle;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

