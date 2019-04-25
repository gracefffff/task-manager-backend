package it.sevenbits.taskmanager.web.controllers;
import it.sevenbits.taskmanager.core.model.Task;
import it.sevenbits.taskmanager.core.repository.ITaskRepository;
import it.sevenbits.taskmanager.web.models.UpdateTaskRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.List;


import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class TasksControllersTest {
    private TasksController tasksController;
    private ITaskRepository taskRepository;
    private final String taskID = "18f877b2-b79f-4751-9e41-dbb5b7301921";


    private Task task;

    @Before
    public void before() {
        task = mock(Task.class);
        taskRepository = mock(ITaskRepository.class);
        tasksController = new TasksController(taskRepository);
        task = mock(Task.class);
    }

    @Test
    public void testList() {
        String taskStatus = "done";
        List<Task> mockListTasks = mock(List.class);
        when(taskRepository.getAllTaskByStatus(anyString())).thenReturn(mockListTasks);
        ResponseEntity answer = tasksController.list(taskStatus);
        verify(taskRepository, times(1)).getAllTaskByStatus(anyString());
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        Assert.assertSame(mockListTasks, answer.getBody());
    }

    @Test
    public void testGetTask() {
        when(taskRepository.getTask(anyString())).thenReturn(task);
        ResponseEntity answer = tasksController.getTask(taskID);
        verify(taskRepository, times(1)).getTask(taskID);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
        Assert.assertSame(task, answer.getBody());
    }

    @Test
    public void testDeleteTask() {
        when(taskRepository.deleteTask(taskID)).thenReturn(task);
        ResponseEntity answer = tasksController.deleteTask(taskID);
        verify(taskRepository, times(1)).deleteTask(taskID);
        assertEquals(HttpStatus.OK, answer.getStatusCode());
    }

    @Test
    public void testUpdateTask() {
        String taskStatus = "inbox";
        String taskText = "to do my test";
        UpdateTaskRequest updateTaskRequest = mock(UpdateTaskRequest.class);
        when(taskRepository.isTaskExist(taskID)).thenReturn(true);
        when(updateTaskRequest.getText()).thenReturn(taskText);
        when(task.getStatus()).thenReturn(taskStatus);
        when(taskRepository.updateTask(taskID, updateTaskRequest)).thenReturn(task);
        when(taskRepository.getTask(taskID)).thenReturn(task);
        ResponseEntity answer = tasksController.updateTask(updateTaskRequest, taskID);
        Assert.assertEquals(HttpStatus.NO_CONTENT, answer.getStatusCode());
    }
}
