package application;

import application.controller.MainController;
import application.view.ViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class ServerInstaller extends Application {

    final static String PATH_TO_SETUP_INSTALLER_FXML = "view/ServerInstaller.fxml";

    private Stage mainStage;
    private MainController mainController;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("ServerInstaller.fxml"));

        // Settings.load();

        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle(" --  Time Task Manager Server Installer  -- ");
        mainStage = primaryStage;

//        URL resource = getClass().getResource(PATH_TO_SETUP_INSTALLER_FXML);

//        FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_TO_SETUP_WIZARD_FXML));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServerInstaller.class.getResource(PATH_TO_SETUP_INSTALLER_FXML));

        try{
            AnchorPane pane = (AnchorPane)loader.load();

            ViewController viewController = loader.getController();
            mainController = new MainController(mainStage, this, viewController);
//            mainController.setViewController(viewController);
//            mainController.getViewController().setMainController(mainController);
//            mainController.setServerInstaller(this);
//            mainController.setStage(mainStage);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

//        this.mainStage = primaryStage;

//        mainStage.setScene(new Scene(root, 600, 400));

                // Даём контроллеру доступ к главному приложению.
//        SetupWizardController controller = loader.getController();
//        controller.setSetupWizard(this);

//        mainStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
