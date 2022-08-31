package com.cleanup.todoc.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.database.dao.ProjectDao;
import com.cleanup.todoc.model.Project;

import java.util.List;

public class ProjectRepository {

    private LiveData<List<Project>> listProjects;

    private final ProjectDao projectDao;

    public ProjectRepository(Context context){
        TodocDatabase database = TodocDatabase.getInstance(context);
        projectDao = database.projectDao();
        listProjects = projectDao.getProjects();
    }

    public LiveData<List<Project>> getProjects(){
        return listProjects;
    }

    public void createProject(Project project){
        TodocDatabase.databaseWriteExecutor.execute(() -> {
            projectDao.createProject(project);
            listProjects = projectDao.getProjects();
        });
    }

    public void deleteProject (Project project){
        TodocDatabase.databaseWriteExecutor.execute(() -> {
            projectDao.deleteProject(project.getId());
            listProjects = projectDao.getProjects();
        });
    }
}
