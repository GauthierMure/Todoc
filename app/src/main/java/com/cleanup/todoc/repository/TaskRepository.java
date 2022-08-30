package com.cleanup.todoc.repository;

import com.cleanup.todoc.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private List<Task> taskList;

    public TaskRepository (){
        taskList = new ArrayList<>();
    }

    public static TaskRepository getInstance(){
        return new TaskRepository();
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(Long id) {
        for (int i = 0; i < taskList.size(); i++){
            if (taskList.get(i).getId() == id)
                return taskList.get(i);
        }
        return null;
    }

    public void createTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
    }
}
