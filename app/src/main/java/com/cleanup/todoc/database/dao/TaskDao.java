package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM tasks_table")
    LiveData<List<Task>> getTasks();

    @Insert
    void createTask (Task task);

    @Update
    void updateTask (Task task);

    @Query("DELETE FROM tasks_table WHERE id = :id")
    void deleteTask (Long id);
}
