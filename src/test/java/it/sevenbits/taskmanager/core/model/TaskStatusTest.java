package it.sevenbits.taskmanager.core.model;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TaskStatusTest {
    @Test
    public void testTaskStatusModel() {
        assertTrue(TaskStatus.isExists("inbox"));
        assertTrue(TaskStatus.isExists("done"));
        assertFalse(TaskStatus.isExists("inboxx"));
        assertFalse(TaskStatus.isExists(""));
    }
}
