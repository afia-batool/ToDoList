package com.example.todolist.Model;

public class ToDoModel {
    private final String task;
    private int status;

    public ToDoModel(String task, int status) {
        this.task = task;
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
