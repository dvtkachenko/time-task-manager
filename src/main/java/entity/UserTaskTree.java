package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diman on 19.04.2017.
 */

// it is a layer for work with data of TIMETASKMANAGER

public class UserTaskTree {

    public UserData userData = null;

    public boolean isUserAuthorised = false;

    List<TaskNode> tasks = null;

    public UserTaskTree() {
        userData = new UserData();
        tasks = new ArrayList<TaskNode>();
    }

    public List<TaskData> toList() {
        List<TaskData> tasksAsList = null;
        if (tasks == null) return tasksAsList;

        tasksAsList = new ArrayList<TaskData>();

        for(TaskNode taskNode : tasks) {
            getAll(tasksAsList, taskNode);
        }

        return tasksAsList;
    }

    private void getAll(List<TaskData> tasksAsList, TaskNode parentNode) {
        List<TaskNode> childNode = parentNode.childTaskNodes;

        if (childNode != null) {
            for (TaskNode tNode : childNode) {
                getAll(tasksAsList, tNode);
            }
            tasksAsList.add(parentNode.taskData);
        } else {
            tasksAsList.add(parentNode.taskData);
        }
    }

    public void testFillUserTaskTree(int userID) {

        userData.setUserName("dima");
        userData.setUserPassword("12345");

        int counterOfTask = 0;

        int index1 = 0;
        for (int i = 1; i < 6; i++) {
            tasks.add(new TaskNode(null, new TaskData(i,0,userID)));
            counterOfTask++;
            System.out.println(tasks.get(index1).taskData);
            List<TaskNode> listLevel1 = tasks.get(index1).childTaskNodes;
            listLevel1 = new ArrayList<TaskNode>();
            tasks.get(index1).childTaskNodes = listLevel1;
            index1++;

            int index2 = 0;
            for (int i1 = i*100 ; i1 < i*100+3 ; i1++) {

                listLevel1.add(new TaskNode(tasks.get(index1-1), new TaskData(i1,i,userID)));
                counterOfTask++;
                System.out.println(listLevel1.get(index2).taskData);
                List<TaskNode> listLevel2 = listLevel1.get(index2).childTaskNodes;
                listLevel2 = new ArrayList<TaskNode>();
                listLevel1.get(index2).childTaskNodes = listLevel2;
                index2++;

                int index3 = 0;
                for (int i2 = i*1000+i1*10; i2 < i*1000+i1*10+5; i2++) {
                    listLevel2.add(new TaskNode(listLevel1.get(index2-1), new TaskData(i2,i1,userID)));
                    counterOfTask++;
                    System.out.println(listLevel2.get(index3).taskData);
                    index3++;
                    //
                }
            }
        }

        System.out.println();
        System.out.println("Total tasks added = " + counterOfTask);

    }
}
