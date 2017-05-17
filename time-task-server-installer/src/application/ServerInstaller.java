package application;

import application.view.ServerInstallerViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;

public class ServerInstaller extends Application {

    final static String PATH_TO_SETUP_WIZARD_FXML = "view/ServerInstaller.fxml";

    Stage mainStage;
    ServerInstallerViewController ServerInstallerViewController;

//    ActivePage activePage = ActivePage.FIRST_PAGE;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        FXMLLoader loader = new FXMLLoader();
//        Parent root = loader.load(getClass().getResource("ServerInstaller.fxml"));

        // Settings.load();

        primaryStage.setResizable(false);

        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setTitle(" --  Time Task Manager Server Installer  -- ");
        mainStage = primaryStage;

        URL resource = getClass().getResource(PATH_TO_SETUP_WIZARD_FXML);

//        FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH_TO_SETUP_WIZARD_FXML));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ServerInstaller.class.getResource("view/ServerInstaller.fxml"));

        try{
            AnchorPane pane = (AnchorPane)loader.load();
            ServerInstallerViewController = loader.getController();
            ServerInstallerViewController.setSetupWizard(this);
            ServerInstallerViewController.setStage(mainStage);
            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch(Exception e){
            e.printStackTrace();
        }

        this.mainStage = primaryStage;

//        mainStage.setScene(new Scene(root, 600, 400));

                // Даём контроллеру доступ к главному приложению.
//        SetupWizardController controller = loader.getController();
//        controller.setSetupWizard(this);

//        mainStage.show();

    }

    public String showFileDialog() {
        String pathToInstall = null;

        return pathToInstall;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
