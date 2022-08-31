package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class TodocDatabase extends RoomDatabase {

    private static volatile TodocDatabase INSTANCE;

    public abstract TaskDao taskDao();

    public static final int NUMBER_OF_THREADS = 4;

    public static TodocDatabase getInstance(Context context){

        if (INSTANCE == null){
            synchronized (TodocDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TodocDatabase.class, "todoc_database.db")
                            .build();
                }
            }
        }

        return INSTANCE;
    }
}
