package com.cleanup.todoc.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.viewmodels.TaskViewModel;

public class TaskViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    private static TaskViewModelFactory factory;

    public static TaskViewModelFactory getInstance(Context context){
        if (factory == null){
            synchronized (TaskViewModelFactory.class){
                if (factory == null)
                    factory = new TaskViewModelFactory(context);
            }
        }
        return factory;
    }

    private TaskViewModelFactory(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TaskViewModel.class)){
            return (T) new TaskViewModel(context);
        }

        throw  new IllegalArgumentException("Unknown ViewModel class");
    }
}
