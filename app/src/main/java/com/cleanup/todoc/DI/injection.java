package com.cleanup.todoc.DI;

import com.cleanup.todoc.repository.TaskRepository;

public class injection {

    public static TaskRepository getTaskRepository(){
        return TaskRepository.getInstance();
    }
}
