package dao.jdbc;

import application.exception.InternalServerErrorException;
import dao.TaskDao;
import dao.exception.DaoException;
import entity.Task;

import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class JdbcTaskDao implements TaskDao {
    private static final String SELECT_TASKS_BY_USER_ID_SQL = "SELECT " +
            "id, taskName, parentTaskId, creationTime, finishTime, suggestedTaskDuration, elapsedTaskDuration, finished " +
            "FROM Tasks WHERE userId=?";

    private static final String INSERT_TASK_SQL = "INSERT INTO Tasks" +
            "(userId, taskName, parentTaskId, creationTime, finishTime, suggestedTaskDuration, elapsedTaskDuration, finished) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_TASK_BY_ID_SQL = "UPDATE Tasks SET " +
            "elapsedTaskDuration=?, finishTime=?, finished=? " +
            "WHERE id=?";

    private static final String DELETE_TASK_BY_ID_SQL = "DELETE FROM Tasks WHERE id=?";

    private static final String SELECT_TASK_ID_BY_USER_ID_AND_CREATION_TIME_SQL = "SELECT id " +
            "FROM Tasks WHERE userId=? AND creationTime=?";

    PreparedStatement selectTasksPreparedStatement;
    PreparedStatement insertTaskPreparedStatement;
    PreparedStatement updateTaskPreparedStatement;
    PreparedStatement deleteTaskPreparedStatement;
    PreparedStatement selectInsertedTaskPreparedStatement;

    List<Task> insertedTaskList;
    List<Long> alreadyInListTaskIds;

    @Override
    public List<Task> getTaskListByUserId(long userId) throws InternalServerErrorException, DaoException{
        try (Connection connection = ConnectionManager.getConnection()) {
            Map<Long, Task> idTaskMap = new HashMap<>();

            selectTasksPreparedStatement = connection.prepareStatement(SELECT_TASKS_BY_USER_ID_SQL);
            selectTasksPreparedStatement.setLong(1, userId);
            ResultSet resultSet = selectTasksPreparedStatement.executeQuery();

            // add all user's tasks to idTaskMap
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String taskName = resultSet.getString("taskName");
                long parentTaskId = resultSet.getLong("parentTaskId");
                LocalDateTime creationTime = LocalDateTime.parse(resultSet.getString("creationTime"));

                String finishTimeString = resultSet.getString("finishTime");
                LocalDateTime finishTime = null;
                if (finishTimeString != null) {
                    finishTime = LocalDateTime.parse(resultSet.getString("finishTime"));
                }

                Duration suggestedTaskDuration = Duration.parse(resultSet.getString("suggestedTaskDuration"));
                Duration elapsedTaskDuration = Duration.parse(resultSet.getString("elapsedTaskDuration"));
                boolean finished = resultSet.getBoolean("finished");

                Task task = new Task(id, userId, taskName, parentTaskId, creationTime, finishTime, suggestedTaskDuration, elapsedTaskDuration, finished);
                idTaskMap.put(id, task);
            }

            // restore parentTask-subTask relations
            List<Task> taskList = new ArrayList<>();
            for (Task currentTask : idTaskMap.values()) {
                long parentTaskId;
                if ((parentTaskId = currentTask.getParentTaskId()) != -1) {
                    Task parentTask = idTaskMap.get(parentTaskId);
                    currentTask.setParentTask(parentTask);
                    parentTask.addSubTask(currentTask);
                } else {
                    taskList.add(currentTask);
                }
            }

            return taskList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Error occurred in process of retrieving task list", e);
        }
    }

    @Override
    public List<Task> updateTaskList(List<Task> taskList) throws InternalServerErrorException, DaoException{
        try (Connection connection = ConnectionManager.getConnection()) {
            deleteTaskPreparedStatement = connection.prepareStatement(DELETE_TASK_BY_ID_SQL);
            updateTaskPreparedStatement = connection.prepareStatement(UPDATE_TASK_BY_ID_SQL);
            insertTaskPreparedStatement = connection.prepareStatement(INSERT_TASK_SQL);
            selectInsertedTaskPreparedStatement = connection.prepareStatement(SELECT_TASK_ID_BY_USER_ID_AND_CREATION_TIME_SQL);

            insertedTaskList = new ArrayList<>();
            alreadyInListTaskIds = new ArrayList<>();

            if (!processTaskList(taskList)){
                throw new DaoException("TaskList Update failed");
            }
            return insertedTaskList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("TaskList Update failed", e);
        }
    }

    private boolean processTaskList(List<Task> taskList) throws SQLException, DaoException {
        for (Task currentTask : taskList) {
            if (!processTask(currentTask)) {
                return false;
            }
        }
        return true;
    }

    private boolean processTask(Task task) throws SQLException, DaoException {
        if (!task.isChanged()) {
            return true;
        }

        if (task.isForDeletion()) {
            deleteTask(task);
            return true;
        } else if (task.getId() != -1) {
            updateTask(task);
        } else {
            insertTask(task);
        }

        return processTaskList(task.getSubTaskList());

    }

    private void insertTask(Task task) throws SQLException, DaoException {
        insertTaskPreparedStatement.setLong(1, task.getUserId());
        insertTaskPreparedStatement.setString(2, task.getTaskName());
        insertTaskPreparedStatement.setLong(3, task.getParentTaskId());
        insertTaskPreparedStatement.setString(4, task.getCreationTime().toString());

        String finishTimeString = null;
        if (task.getFinishTime() != null) {
            finishTimeString = task.getFinishTime().toString();
        }
        insertTaskPreparedStatement.setString(5, finishTimeString);

        insertTaskPreparedStatement.setString(6, task.getSuggestedTaskDuration().toString());
        insertTaskPreparedStatement.setString(7, task.getElapsedTaskDuration().toString());
        insertTaskPreparedStatement.setBoolean(8, task.isFinished());

        if (insertTaskPreparedStatement.executeUpdate() != 1) {
            throw new DaoException("Update failed");
        }

        Task savedTask = selectInsertedTask(task.getUserId(), task.getCreationTime());
        long taskId = savedTask.getId();
        task.setId(taskId);

        if (!alreadyInListTaskIds.contains(task.getParentTaskId())) {
            insertedTaskList.add(task);
        }
        alreadyInListTaskIds.add(taskId);

        for (Task subTask : task.getSubTaskList()) {
            subTask.setParentTaskId(taskId);
        }

        task.setChanged(false);
    }

    private void updateTask(Task task) throws SQLException, DaoException {
        updateTaskPreparedStatement.setString(1, task.getElapsedTaskDuration().toString());

        String finishTimeString = null;
        if (task.getFinishTime() != null) {
            finishTimeString = task.getFinishTime().toString();
        }
        updateTaskPreparedStatement.setString(2, finishTimeString);

        updateTaskPreparedStatement.setBoolean(3, task.isFinished());
        updateTaskPreparedStatement.setLong(4, task.getId());

        if (updateTaskPreparedStatement.executeUpdate() != 1) {
            throw new DaoException("Update failed");
        }
        task.setChanged(false);
    }

    private void deleteTask(Task task) throws SQLException, DaoException {
        if (task.getId() == -1) {
            return;
        }
        deleteTaskPreparedStatement.setLong(1, task.getId());

        if (deleteTaskPreparedStatement.executeUpdate() != 1) {
            throw new DaoException("Update failed");
        }
        task.setChanged(false);
    }

    // get task from DB with id
    // у одного пользователя не может быть две задачи созданные точно в одно и тоже время,
    // поэтому здесь в качестве идентификатора используется userId и время создания
    private Task selectInsertedTask(long userId, LocalDateTime creationTime) throws SQLException, DaoException {
        PreparedStatement preparedStatement = selectInsertedTaskPreparedStatement;
        preparedStatement.setLong(1, userId);
        preparedStatement.setString(2, creationTime.toString());

        ResultSet resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
            throw new DaoException("Can't retrieve inserted task. Update failed");
        }

        Task idOnlyTask = new Task(resultSet.getLong("id"));
        return idOnlyTask;
    }
}
