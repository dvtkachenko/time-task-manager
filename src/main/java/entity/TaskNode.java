package entity;

import java.util.List;

/**
 * Created by diman on 21.04.2017.
 */
public class TaskNode {

    public TaskNode parentTaskNode = null;

    TaskData taskData = null;

    List<TaskNode> childTaskNodes = null;

    TaskNode(TaskNode parentTaskNode, TaskData taskData) {
        this.parentTaskNode = parentTaskNode;
        this.taskData = taskData;
    }
}
