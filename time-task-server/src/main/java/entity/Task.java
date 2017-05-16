package entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Task implements Serializable {

    public static final long serialVersionUID = 123L;

    private long id = -1;
    private long userId;
    private String taskName;
    private long parentTaskId = -1; // -1 if no parent
    private Task parentTask;
    private List<Task> subTaskList = new ArrayList<>(); // можно отложить до появления первой подзадачи
    private LocalDateTime creationTime;
    private LocalDateTime finishTime;
    private Duration suggestedTaskDuration;
    private Duration elapsedTaskDuration;
    private boolean finished;
    private boolean changed;
    private boolean forDeletion;

    public Task() {
    }

    public Task(long id) {
        this.id = id;
    }

    public Task(long userId, String taskName, LocalDateTime creationTime, Duration suggestedTaskDuration, Task parentTask) {
        // желательно сделать проверку на то, чтобы сумарная длительность подзадач не привышала длительность родительской задачи

        this.userId = userId;
        this.taskName = taskName;
        this.creationTime = creationTime;
        this.suggestedTaskDuration = suggestedTaskDuration;
        this.elapsedTaskDuration = Duration.ZERO;
        this.finished = false;
        this.changed = true;

        this.parentTask = parentTask;
        if (parentTask != null){
            this.parentTaskId = parentTask.getId();
            parentTask.getSubTaskList().add(this);
        }
    }

    public Task(long id, long userId, String taskName, long parentTaskId, LocalDateTime creationTime, LocalDateTime finishTime,Duration suggestedTaskDuration, Duration elapsedTaskDuration, boolean finished) {
        this.id = id;
        this.userId = userId;
        this.taskName = taskName;
        this.parentTaskId = parentTaskId;
        this.creationTime = creationTime;
        this.finishTime = finishTime;
        this.suggestedTaskDuration = suggestedTaskDuration;
        this.elapsedTaskDuration = elapsedTaskDuration;
        this.finished = finished;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(long parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public List<Task> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<Task> subTaskList) {
        this.subTaskList = subTaskList;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public Duration getSuggestedTaskDuration() {
        return suggestedTaskDuration;
    }

    public void setSuggestedTaskDuration(Duration suggestedTaskDuration) {
        this.suggestedTaskDuration = suggestedTaskDuration;
    }

    public Duration getElapsedTaskDuration() {
        return elapsedTaskDuration;
    }

    public void setElapsedTaskDuration(Duration elapsedTaskDuration) {
        this.elapsedTaskDuration = elapsedTaskDuration;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public boolean isForDeletion() {
        return forDeletion;
    }

    public void setForDeletion(boolean forDeletion) {
        this.forDeletion = forDeletion;
    }

    public void addSubTask(Task task) {
        this.subTaskList.add(task);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", taskName='" + taskName + '\'' +
                ", parentTaskId=" + parentTaskId +
                ", creationTime=" + creationTime +
                ", suggestedTaskDuration=" + suggestedTaskDuration +
                ", elapsedTaskDuration=" + elapsedTaskDuration +
                ", finished=" + finished +
                ", subTasks=" + subTaskList +
                '}';
    }
}
