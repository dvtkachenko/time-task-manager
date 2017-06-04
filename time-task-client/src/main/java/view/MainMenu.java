package view;

import controller.MainController;
import entity.User;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MainModel;

public class MainMenu {
    private static User user;
    private static Stage primaryStage;
    private static Scene mainScene;
    private MainModel mainModel;
    private MainController mainController;

    public MainMenu() {
        mainModel = new MainModel();
        mainController = new MainController(mainModel);

        mainController.switchTimer();
        mainController.addTask();
        mainController.editTask();
        mainController.removeTask();
        mainController.completeTask();
        mainController.addStatistics();
        mainController.setTasksElapsedTime();
        mainController.setTasksSuggestedTime();

        mainScene = new Scene(mainModel.getPane(), 800, 600);
        mainScene.getStylesheets().add(
                MainMenu.class.getResource("/css/Main.css").toExternalForm());
    }

    public MainModel getMainModel() {
        return mainModel;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static Scene getScene() {
        return mainScene;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MainMenu.user = user;
    }
}
