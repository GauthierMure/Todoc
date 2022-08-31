package com.cleanup.todoc.factory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.viewmodels.ProjectViewModel;

public class ProjectViewModelFactory implements ViewModelProvider.Factory{

    private Context context;

    private static ProjectViewModelFactory factory;

    public static ProjectViewModelFactory getInstance(Context context){
        if (factory == null){
            synchronized (ProjectViewModelFactory.class){
                if (factory == null)
                    factory = new ProjectViewModelFactory(context);
            }
        }
        return factory;
    }

    private ProjectViewModelFactory (Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProjectViewModel.class)){
            return (T) new ProjectViewModel(context);
        }
        throw  new IllegalArgumentException("Unknown ViewModel class");
    }
}
