package view;

import entity.Task;
import entity.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import menu.MainMenu;
import message.Message;
import message.MessageType;
import network.ClientSocket;

/**
 * Created by Andrey on 05.05.2017.
 */
public class TaskForm extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Task Form");

        GridPane grid = new GridPane();
        grid.setId("taskForm");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(25);

        Text sceneTitle = new Text("Add Task");
        sceneTitle.setId("task-title");
        HBox title = new HBox(10);
        title.setAlignment(Pos.CENTER);
        title.getChildren().add(sceneTitle);
        grid.add(title, 0, 1, 2, 1);

        Label userName = new Label("Task Name:");
        grid.add(userName, 0, 2);
        TextField taskName = new TextField();
        grid.add(taskName, 1, 2);

        Label password = new Label("Suggested Task Duration:");
        password.setWrapText(true);
        grid.add(password, 0, 3);
        TextField suggestedTaskDuration = new TextField();
        grid.add(suggestedTaskDuration, 1, 3);


        Label taskCommentLabel = new Label("Task Comment:");
        grid.add(taskCommentLabel, 0, 4);
        TextArea taskComment = new TextArea();
        taskComment.setPrefColumnCount(2);
        taskComment.setPrefRowCount(6);
        grid.add(taskComment, 1, 4);

        Button add = new Button("Add");
        Button cancel = new Button("Cancel");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(cancel);
        hbBtn.getChildren().add(add);
        grid.add(hbBtn, 1, 5);


        Text hint = new Text();
        hint.setId("warning");
        HBox hbMessage = new HBox(10);
        hbMessage.setAlignment(Pos.BOTTOM_RIGHT);
        hbMessage.getChildren().add(hint);
        grid.add(hbMessage, 0, 6, 2, 1);

        add.setOnAction(e -> {
            if (taskName.getText().trim().isEmpty() || suggestedTaskDuration.getText().trim().isEmpty()) {
                hint.setText("You need to enter all fields");
            } else {
                hint.setId("success");

//                Task task = new Task(taskName.getText().trim(), suggestedTaskDuration.getText().trim(), taskComment.getText().trim());

                Timeline timeline = new Timeline(new KeyFrame(
                        Duration.millis(1000),
                        ae -> primaryStage.hide()));
                timeline.play();
            }
        });

        Scene scene = new Scene(grid, 600, 350);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(TaskForm.class.getResource("/css/Login.css").toExternalForm());

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
