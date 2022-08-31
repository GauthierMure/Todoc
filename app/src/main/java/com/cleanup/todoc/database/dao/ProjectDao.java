package com.cleanup.todoc.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.Project;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query("SELECT * FROM project_table")
    LiveData<List<Project>> getProjects();

    @Insert
    void createProject(Project project);

    @Update
    void updateProject(Project project);

    @Query("DELETE FROM project_table WHERE id = :id")
    void deleteProject(Long id);
}
