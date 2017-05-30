package launcher;

import javafx.application.Application;
import menu.TimeTaskClient;
import view.MainMenu;

public class Launcher {
    public static void main(String[] args) {
        Application.launch(TimeTaskClient.class, args);
        MainMenu.getPrimaryStage().setOnCloseRequest(event -> {
            System.out.println("Stage is closing");
        });
    }
}
