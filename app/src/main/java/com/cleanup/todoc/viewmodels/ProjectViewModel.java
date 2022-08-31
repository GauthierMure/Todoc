package com.cleanup.todoc.viewmodels;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.DI.injection;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.repository.ProjectRepository;

import java.util.List;

public class ProjectViewModel extends ViewModel {

    private ProjectRepository projectSource;

    public ProjectViewModel (Context context){
        projectSource = injection.getProjectRepository(context);
    }

    public LiveData<List<Project>> getProjects (){
        return projectSource.getProjects();
    }

    public void createProject (Project project){
        projectSource.createProject(project);
    }

    public void deleteProject (Project project){
        projectSource.deleteProject(project);
    }
}
