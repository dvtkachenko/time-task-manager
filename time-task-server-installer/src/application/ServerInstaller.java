package application;

import application.controller.MainController;
import application.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by diman on 19.05.2017.
 *
 * This class is a root application and entry point
 * It creates, keeps and starts instance of main controller
 *
 */

public class ServerInstaller extends Application {

    final static String PATH_TO_SETUP_INSTALLER_FXML = "view/ServerInstaller.fxml";

    private MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle(" --  Time Task Manager Server Installer  -- ");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServerInstaller.class.getResource(PATH_TO_SETUP_INSTALLER_FXML));

        try{
//            AnchorPane pane = (AnchorPane)loader.load();
            AnchorPane pane = loader.load();

            ViewController viewController = loader.getController();
            mainController = new MainController(primaryStage, viewController);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
