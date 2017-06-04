package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import menu.TimeTaskClient;
import message.Message;
import message.MessageType;
import model.LoginModel;
import model.MainModel;
import view.MainMenu;

public class LoginController {
    private final LoginModel loginModel;

    public LoginController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void register() {
        loginModel.getRegisterButton().setOnAction(e -> {
            if (loginModel.getUserField().getText().trim().isEmpty() ||
                    loginModel.getPasswordField().getText().trim().isEmpty()) {
                loginModel.getHint().setText("You need to enter all fields");
            } else {
                Message responseMessage = TimeTaskClient.getClientSocket().sendMessage(
                        loginModel.getUserField().getText(), loginModel.getPasswordField().getText(),
                        MessageType.CREATE_MESSAGE, "register"
                );
                MainMenu.setUser(responseMessage.getUser());

                if (responseMessage.getMessageType().equals(MessageType.FAIL_MESSAGE)) {
                    loginModel.getHint().setText("Already registered");
                } else if (responseMessage.getMessageType().equals(MessageType.CREATE_SUCCESS_MESSAGE)) {
                    loginModel.getHint().setId("success");
                    loginModel.getHint().setText("Successfully registered");
                    MainMenu mainMenu = new MainMenu();
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(1000),
                            ae -> MainMenu.getPrimaryStage().setScene(MainMenu.getScene())));
                    MainMenu.getPrimaryStage().setResizable(true);
                    timeline.play();
                }
            }
        });
    }

    public void login() {
        loginModel.getLoginButton().setOnAction(e -> {
            if(loginModel.getUserField().getText().trim().isEmpty() ||
                    loginModel.getPasswordField().getText().trim().isEmpty()) {
                loginModel.getHint().setText("You need to enter all fields");
            } else {
                Message responseMessage = TimeTaskClient.getClientSocket().sendMessage(
                        loginModel.getUserField().getText(), loginModel.getPasswordField().getText(),
                        MessageType.LOGIN_MESSAGE, "log in"
                );
                MainMenu.setUser(responseMessage.getUser());

                if (responseMessage.getMessageType().equals(MessageType.FAIL_MESSAGE)) {
                    loginModel.getHint().setText("Wrong username or password");
                } else if (responseMessage.getMessageType().equals(MessageType.LOGIN_SUCCESS_MESSAGE)) {
                    loginModel.getHint().setId("success");
                    loginModel.getHint().setText("Logged in successfully");
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.getMainModel().loadTreeView(MainMenu.getUser().getTaskList(),
                            MainModel.getTreeView().getRoot());
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(1000),
                            ae -> MainMenu.getPrimaryStage().setScene(MainMenu.getScene())));
                    MainMenu.getPrimaryStage().setResizable(true);
                    timeline.play();
                }
            }
        });
    }
}
