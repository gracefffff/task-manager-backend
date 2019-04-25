package it.sevenbits.taskmanager.core.repository;

import it.sevenbits.taskmanager.core.model.Task;
import it.sevenbits.taskmanager.core.model.TaskStatus;
import it.sevenbits.taskmanager.web.models.UpdateTaskRequest;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;
import java.util.UUID;

import static it.sevenbits.taskmanager.core.service.TimeService.getCurrentTime;

/**
 * this is class is representation of the interface ITaskRepository which working with database
 */
public class DatabaseTasksRepository implements ITaskRepository {
    private final JdbcOperations tasks;

    /**
     * this is constructor of this class
     *
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
                "INSERT INTO task (id, text, status, createdat, updatedat) VALUES (?, ?, ?, ?,?)",
                newTask.getId(), newTask.getText(), newTask.getStatus(), newTask.getCreatedAt(), newTask.getUpdatedAt()
        );

        return newTask;
    }

    @Override
    public List<Task> getAllTaskByStatus(final String status) {
        return tasks.query(
                "SELECT id, text, createdat, updatedat FROM task WHERE status = ?",
                (resultSet, i) -> {
                    String id = resultSet.getString(1);
                    String text = resultSet.getString(2);
                    String createdAt = resultSet.getString(3);
                    String updatedAt = resultSet.getString(4);
                    return new Task(id, text, TaskStatus.valueOf(status), createdAt, updatedAt);
                }, status);

    }



    @Override
    public Task getTask(final String id) {
        try {
            return tasks.queryForObject(
                    "SELECT id, text, status, createdat, updatedat FROM task WHERE id = ?",
                    (resultSet, i) -> {
                        String rowId = resultSet.getString("id");
                        String rowName = resultSet.getString(2);
                        String rowStatus = resultSet.getString(3);
                        String rowCreatedAt = resultSet.getString(4);
                        String rowUpdatedAt = resultSet.getString(5);
                        return new Task(rowId, rowName, TaskStatus.valueOf(rowStatus), rowCreatedAt, rowUpdatedAt);
                    },
                    id);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }

    }

    @Override
    public Task deleteTask(final String id) {
        try {
            return tasks.queryForObject(
                    "DELETE  FROM task WHERE id = ? RETURNING id, text, status, createdat, updatedat",
                    (resultSet, i) -> {
                        String rowId = resultSet.getString(1);
                        String rowName = resultSet.getString(2);
                        String rowStatus = resultSet.getString(3);
                        String rowCreatedAt = resultSet.getString(4);
                        String rowUpdatedAt = resultSet.getString(5);
                        return new Task(rowId, rowName, TaskStatus.valueOf(rowStatus), rowCreatedAt, rowUpdatedAt);
                    },
                    id);
        } catch (IncorrectResultSizeDataAccessException ex) {
            return null;
        }
    }

    @Override
    public boolean isTaskExist(final String id) {
        return getTask(id) != null;

    }

    @Override
    public Task updateTask(final String id, final UpdateTaskRequest task) {

        tasks.update(
                "UPDATE task SET text = COALESCE (?,?), status = COALESCE (?,?), updatedAt = ? WHERE id = ?",
                task.getText(), getTask(id).getText(), task.getStatus(), getTask(id).getStatus(), getCurrentTime(), id
        );
        return getTask(id);

    }
}
