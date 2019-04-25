package it.sevenbits.taskmanager.core.model;

import org.junit.Before;
import org.junit.Test;

import static it.sevenbits.taskmanager.core.service.TimeService.getCurrentTime;
import static junit.framework.TestCase.assertEquals;

public class TaskTest {
    private final String id = "1";
    private final String text = "to do my test";
    private final String taskStatus = "inbox";
    private final String createdAt = getCurrentTime();
    private final String updateAt = createdAt;
    private Task task;

    @Before
    public void before() {
        task = new Task(id, text, TaskStatus.valueOf(taskStatus), createdAt, updateAt);
    }

    @Test
    public void testTaskModel() {
        assertEquals(id, task.getId());
        assertEquals(text, task.getText());
        assertEquals(taskStatus, task.getStatus());
        assertEquals(createdAt, task.getCreatedAt());
        assertEquals(updateAt, task.getUpdatedAt());
    }

}
