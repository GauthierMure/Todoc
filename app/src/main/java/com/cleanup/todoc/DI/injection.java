package com.cleanup.todoc.DI;

import android.content.Context;

import com.cleanup.todoc.repository.TaskRepository;

public class injection {

    public static TaskRepository getTaskRepository(Context context){
        return new TaskRepository(context);
    }
}
