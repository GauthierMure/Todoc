package com.cleanup.todoc.database;

import static org.junit.Assert.assertTrue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.cleanup.todoc.database.TodocDatabase;
import com.cleanup.todoc.model.Project;
import com.cleanup.todoc.model.Task;
import com.cleanup.todoc.utils.LiveDataTestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {

    private TodocDatabase database;

    private static Long ID = 1L;

    private static Project PROJECT = new Project(1L, "Projet Tartampion", 0xFFEADAD1);
    private static Task TASK = new Task(1L,1L, "test", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void initDb() throws Exception {
        this.database = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                TodocDatabase.class).allowMainThreadQueries().build();
    }

    @After
    public void closeDb() throws Exception {
        database.close();
    }

    @Test
    public void should_insert_Project() throws InterruptedException {
        this.database.projectDao().createProject(PROJECT);
        List<Project> projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
        Project project = projects.get(0);

        assertTrue(project.getName().equals(PROJECT.getName()) && project.getId() == ID);
    }

    @Test
    public void should_delete_Project() throws InterruptedException {
        this.database.projectDao().createProject(PROJECT);
        List<Project> projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());
        Project project = projects.get(0);

        database.projectDao().deleteProject(project.getId());
        projects = LiveDataTestUtils.getValue(this.database.projectDao().getProjects());

        assertTrue(projects.isEmpty());
    }

    @Test
    public void should_insert_Task() throws InterruptedException {
        this.database.projectDao().createProject(PROJECT);
        this.database.taskDao().createTask(TASK);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        Task task = tasks.get(0);

        assertTrue(task.getName().equals(TASK.getName()) && task.getId() == 1L);
    }

    @Test
    public void should_delete_Task() throws InterruptedException {
        this.database.projectDao().createProject(PROJECT);
        this.database.taskDao().createTask(TASK);
        List<Task> tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());
        Task task = tasks.get(0);

        this.database.taskDao().deleteTask(task.getId());
        tasks = LiveDataTestUtils.getValue(this.database.taskDao().getTasks());

        assertTrue(tasks.isEmpty());
    }
}
