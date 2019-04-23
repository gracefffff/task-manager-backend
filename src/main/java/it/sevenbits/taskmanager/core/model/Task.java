package it.sevenbits.taskmanager.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import static it.sevenbits.taskmanager.core.service.TimeService.getCurrentTime;

/**
 * this class is representation of the Task
 */
public class Task {
    private final String id;
    private String text;
    private TaskStatus status;
    private String createdAt;

    /**
     * this is constructor of this class
     *
     * @param id     - this is ID of the Task
     * @param text   - this is text or text of the Task
     * @param status - this is a status of Task
     */
    @JsonCreator
    public Task(final @JsonProperty("id") String id, final @JsonProperty("text") String text, final @JsonProperty("status") TaskStatus status) {
        this.id = id;
        this.text = text;
        this.status = status;
        createdAt = getCurrentTime();
    }

    /**
     * this is overloaded constructor of this class
     *
     * @param id        this is ID of the Task
     * @param text      this is text or text of the Task
     * @param status    this is a status of Task
     * @param createdAt this is a time-date of creating tasks
     */
    @JsonCreator
    public Task(final @JsonProperty("id") String id, final @JsonProperty("text") String text, final @JsonProperty("status") TaskStatus status, final String createdAt) {
        this.id = id;
        this.text = text;
        this.status = status;
        this.createdAt = createdAt;
    }

    /**
     * this is function which return id of the current task
     *
     * @return String value id of this Task
     */
    public String getId() {
        return id;
    }

    /**
     * this function returns time-date of the creating  current task
     *
     * @return String value time-date of the creating  current task
     */
    public String getCreatedAt() {
        return createdAt;
    }


    /**
     * this function is return text of the task
     *
     * @return String value text of task
     */
    public String getText() {
        return text;
    }

    /**
     * this is function which allows setting the text of the task
     *
     * @param newName - String value new text of the task
     */
    public void setText(final String newName) {
        this.text = newName;
    }

    /**
     * this is function which allows setting the status of the task
     *
     * @param newStatus - String value new status of the task
     */
    public void setStatus(final String newStatus) {
        status = TaskStatus.valueOf(newStatus);
    }

    /**
     * this function is return status of the current task
     *
     * @return String value status
     */
    public String getStatus() {
        return status.toString();
    }


}

