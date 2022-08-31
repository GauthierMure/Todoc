package com.cleanup.todoc.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.DI.injection;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends ViewModel {

    private final TaskRepository taskSource;

    public TaskViewModel(Context context){
        taskSource = injection.getTaskRepository(context);
    }

    public LiveData<List<Task>> getListTask() {
        return taskSource.getTaskList();
    }

    public void createTask(Task task){
        taskSource.createTask(task);
    }

    public void deleteTask(Task task) {
        taskSource.deleteTask(task);
    }
}
