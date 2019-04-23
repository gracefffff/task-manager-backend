package it.sevenbits.taskmanager.core.repository;

import it.sevenbits.taskmanager.core.model.Task;
import it.sevenbits.taskmanager.core.model.TaskStatus;
import it.sevenbits.taskmanager.web.models.UpdateTaskRequest;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

/**
 * this is class is representation of the interface ITaskRepository which working with database
 */
public class DatabaseTasksRepository implements ITaskRepository {
    private final JdbcOperations tasks;

    /**
     * this is constructor of this class
     * @param jdbcOperations database where tasks will be
     */
    public DatabaseTasksRepository(final JdbcOperations jdbcOperations) {
        tasks = jdbcOperations;
    }

    @Override
    public List<Task> getAllTasks() {
        return null;
    }

    @Override
    public Task create(final String text) {
        Task newTask = new Task(UUID.randomUUID().toString(), text, TaskStatus.inbox);
        int rows = tasks.update(
                "INSERT INTO task (id, text, status, createdat) VALUES (?, ?, ?, ?)",
                newTask.getId(), newTask.getText(), newTask.getStatus(), newTask.getCreatedAt()
        );

        return newTask;
    }

    @Override
    public List<Task> getAllTaskByStatus(final String status) {
        return tasks.query(
                "SELECT id, text, createdat FROM task WHERE status=?",
                (resultSet, i) -> {
                    String id = resultSet.getString(1);
                    String text = resultSet.getString(2);
                    String createdAt = resultSet.getString(3);
                    return new Task(id, text, TaskStatus.valueOf(status), createdAt);
                }, status);

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Task getTask(final String id) {
        return null;
    }

    @Override
    public Task deleteTask(final String id) {
        return null;
    }

    @Override
    public boolean isTaskExist(final String id) {
        return false;
    }

    @Override
    public Task updateTask(final String id, final UpdateTaskRequest task) {
        return null;
    }
}
