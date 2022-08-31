package com.cleanup.todoc.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Task;

import java.util.List;

public class TaskRepository {

    private LiveData<List<Task>> listTasks;

    private final TaskDao taskDao;

    public TaskRepository (Context context){
        TodocDatabase database = TodocDatabase.getInstance(context);
        taskDao = database.taskDao();
        listTasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getTaskList() {
        return listTasks;
    }

    public void createTask(Task task) {
        TodocDatabase.databaseWriteExecutor.execute(() ->
                taskDao.createTask(task));
        listTasks = taskDao.getTasks();
    }

    public void deleteTask(Task task) {
        TodocDatabase.databaseWriteExecutor.execute(() ->
                taskDao.deleteTask(task.getId()));
        listTasks = taskDao.getTasks();
    }
}
