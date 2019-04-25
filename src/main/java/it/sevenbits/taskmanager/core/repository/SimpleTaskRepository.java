package it.sevenbits.taskmanager.core.repository;

import it.sevenbits.taskmanager.core.model.Task;
import it.sevenbits.taskmanager.core.model.TaskStatus;
import it.sevenbits.taskmanager.web.models.UpdateTaskRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * this is class is representation of the interface ITaskRepository
 */
public class SimpleTaskRepository implements ITaskRepository {
    private Map<String, Task> tasks;

    /**
     * this is construct of this class
     *
     * @param tasks - tasks which was stored in this repository
     */
    public SimpleTaskRepository(final Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<Task> getAllTasks() {

        return Collections.unmodifiableList(new ArrayList<>(tasks.values()));
    }

    /**
     * this function returns list of Task with the same status
     *
     * @param status -string value status of task
     * @return list of tasks with  equals status
     */
    public List<Task> getAllTaskByStatus(final String status) {
        ArrayList<Task> selectedTasks = new ArrayList<>();
        for (Task task : tasks.values()
        ) {
            if (task.getStatus().equals(status)) {
                selectedTasks.add(task);
            }
        }
        return selectedTasks;
    }

    @Override
    public Task create(@NotBlank @NotNull final String text) {
        Task newTask = new Task(UUID.randomUUID().toString(), text, TaskStatus.inbox);
        tasks.put(newTask.getId(), newTask);
        return newTask;
    }

    @Override
    public Task getTask(final String id) {
        return tasks.get(id);
    }

    @Override
    public Task deleteTask(final String id) {
        return tasks.remove(id);
    }

    @Override
    public Task updateTask(final String id, final UpdateTaskRequest updateTask) {

        Task currentTask = tasks.get(id);
        currentTask.setText(updateTask.getText() == null ? currentTask.getText() : updateTask.getText());
        currentTask.setStatus(updateTask.getStatus() == null ? currentTask.getStatus() : updateTask.getStatus());
        return currentTask;
    }

    @Override
    public boolean isTaskExist(final String id) {
        return tasks.containsKey(id);
    }

}