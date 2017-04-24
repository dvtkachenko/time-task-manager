package com.time_task_manager.model;

import java.sql.Date;

/**
 * Created by diman on 21.04.2017.
 */

public class TaskData {

	private int taskID;
	private int parentTaskID;
	private int childTaskID;
	private int taskLevel;
	private int userID;
	private String taskName;
	private Date planStartTime;
	private Date planEndTime;
	private int planDuration;
	private Date factStartTime;
	private Date factEndTime;
	private int factDuration;
	private int numVersion;
	private String isCompleted;

	public TaskData() {
		super();
	}

	public TaskData(int taskID, int userID) {
		this.taskID = taskID;
		this.userID = userID;
	}

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public int getParentTaskID() {
		return parentTaskID;
	}

	public void setParentTaskID(int parentTaskID) {
		this.parentTaskID = parentTaskID;
	}

	public int getChildTaskID() {
		return childTaskID;
	}

	public void setChildTaskID(int childTaskID) {
		this.childTaskID = childTaskID;
	}

	public int getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(int taskLevel) {
		this.taskLevel = taskLevel;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Date getPlanStartTime() {
		return planStartTime;
	}

	public void setPlanStartTime(Date planStartTime) {
		this.planStartTime = planStartTime;
	}

	public Date getPlanEndTime() {
		return planEndTime;
	}

	public void setPlanEndTime(Date planEndTime) {
		this.planEndTime = planEndTime;
	}

	public int getPlanDuration() {
		return planDuration;
	}

	public void setPlanDuration(int planDuration) {
		this.planDuration = planDuration;
	}

	public Date getFactStartTime() {
		return factStartTime;
	}

	public void setFactStartTime(Date factStartTime) {
		this.factStartTime = factStartTime;
	}

	public Date getFactEndTime() {
		return factEndTime;
	}

	public void setFactEndTime(Date factEndTime) {
		this.factEndTime = factEndTime;
	}

	public int getFactDuration() {
		return factDuration;
	}

	public void setFactDuration(int factDuration) {
		this.factDuration = factDuration;
	}

	public int getNumVersion() {
		return numVersion;
	}

	public void setNumVersion(int numVersion) {
		this.numVersion = numVersion;
	}

	public String isCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}

	@Override
	public String toString() {
		return "TaskData{" +
				"taskID=" + taskID +
				", parentTaskID=" + parentTaskID +
				", childTaskID=" + childTaskID +
				", taskLevel=" + taskLevel +
				", userID=" + userID +
				", taskName='" + taskName + '\'' +
				", planStartTime=" + planStartTime +
				", planEndTime=" + planEndTime +
				", planDuration=" + planDuration +
				", factStartTime=" + factStartTime +
				", factEndTime=" + factEndTime +
				", factDuration=" + factDuration +
				", numVersion=" + numVersion +
				", isCompleted='" + isCompleted + '\'' +
				'}' + '\n';
	}
}