package it.sevenbits.taskmanager.core.repository;

import it.sevenbits.taskmanager.core.model.Task;
import it.sevenbits.taskmanager.web.models.UpdateTaskRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;


import java.util.List;


import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class DatabaseTasksRepositoryTest {
    private JdbcOperations mockJdbcOperations;
    private DatabaseTasksRepository databaseTasksRepository;


    @Before
    public void setup() {
        mockJdbcOperations = mock(JdbcOperations.class);
        databaseTasksRepository = new DatabaseTasksRepository(mockJdbcOperations);
    }

    @Test
    public void testGetTask() {
        String taskID = "18f877b2-b79f-4751-9e41-dbb5b7301921";
        Task mockTask = mock(Task.class);
        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(mockTask);
        Task expectedTask = databaseTasksRepository.getTask(taskID);
        verify(mockJdbcOperations, times(1)).queryForObject(
                eq("SELECT id, text, status, createdat, updatedat FROM task WHERE id = ?"),
                any(RowMapper.class),
                eq(taskID)
        );
        Assert.assertEquals(mockTask, expectedTask);
    }

    @Test
    public void testGetAllTasksByStatus() {
        String taskStatus = "done";
        List<Task> mockListTasks = mock(List.class);
        when(mockJdbcOperations.query(anyString(), any(RowMapper.class), any())).thenReturn(mockListTasks);
        List<Task> expectedList = databaseTasksRepository.getAllTaskByStatus(taskStatus);
        verify(mockJdbcOperations, times(1)).query(
                eq("SELECT id, text, createdat, updatedat FROM task WHERE status = ?"),
                any(RowMapper.class),
                eq(taskStatus)
        );
        Assert.assertSame(expectedList, mockListTasks);
    }

    @Test
    public void testCreate() {
        String taskText = "to do my test";
        Task mockTask = mock(Task.class);
        when(mockJdbcOperations.update(anyString(), anyString())).thenReturn(1);
        when(mockTask.getText()).thenReturn(taskText);
        Task expectedTask = databaseTasksRepository.create(taskText);
        Assert.assertEquals(mockTask.getText(), expectedTask.getText());
    }

    @Test
    public void testDeleteTask() {
        String taskID = "18f877b2-b79f-4751-9e41-dbb5b7301921";
        Task mockTask = mock(Task.class);
        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), anyString())).thenReturn(mockTask);
        Task expectedTask = databaseTasksRepository.deleteTask(taskID);
        verify(mockJdbcOperations, times(1)).queryForObject(
                eq("DELETE  FROM task WHERE id = ? RETURNING id, text, status, createdat, updatedat"),
                any(RowMapper.class),
                eq(taskID)
        );
        Assert.assertEquals(mockTask, expectedTask);
    }

    @Test
    public void testUpdateTask() {
        String taskID = "18f877b2-b79f-4751-9e41-dbb5b7301921";
        UpdateTaskRequest updateTask = mock(UpdateTaskRequest.class);
        Task mockTask = mock(Task.class);
        when(mockJdbcOperations.queryForObject(anyString(), any(RowMapper.class), any())).thenReturn(mockTask);
        when(mockJdbcOperations.update(anyString(), anyString())).thenReturn(1);
        Task expectedTask = databaseTasksRepository.updateTask(taskID, updateTask);
        Assert.assertEquals(mockTask, expectedTask);
    }

}
