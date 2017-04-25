package entity;

import java.util.List;

/**
 * Created by diman on 21.04.2017.
 */
public class TaskNode {

    private TaskNode parentTaskNode;

    TaskData taskData;

    List<TaskNode> childTaskNodes;
}
