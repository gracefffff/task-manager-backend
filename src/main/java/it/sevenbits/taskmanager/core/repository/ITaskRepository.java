package it.sevenbits.taskmanager.core.repository;

import it.sevenbits.taskmanager.core.model.Task;
import it.sevenbits.taskmanager.web.models.UpdateTaskRequest;

import java.util.List;

/**
 * this is interface which describe TaskRepository, repository where tasks are stored
 */
public interface ITaskRepository {
    /**
     * this is function which return list of all tasks
     *
     * @return List of Tasks
     */
    List<Task> getAllTasks();

    /**
     * this function are created new task
     *
     * @param text - text or name of the task
     * @return new created Task
     */
    Task create(String text);

    /**
     * this function returns list of Task with the same status
     *
     * @param status -string value status of task
     * @return list of tasks with  equals status
     */
    List<Task> getAllTaskByStatus(String status);

    /**
     * this function return task by its id
     *
     * @param id - id of the task
     * @return task if this repository contains this task or null
     */
    Task getTask(String id);

    /**
     * this funciton delete task by its id
     *
     * @param id - id of the task
     * @return task which was deleted
     */
    Task deleteTask(String id);

    /**
     * this function is check existence of task
     *
     * @param id - id of the task
     * @return true if this task is exist
     * false if this task is not exist
     */
    boolean isTaskExist(String id);

    /**
     * this function updates task by name and status
     *
     * @param id   - id of the task
     * @param task - this is object which contains updating new status and name
     * @return updated task
     */
    Task updateTask(String id, UpdateTaskRequest task);


}
