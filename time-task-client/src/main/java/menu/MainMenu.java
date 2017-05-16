package menu;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import message.Message;
import message.MessageType;
import network.ClientSocket;

public class MainMenu extends Application {
    private ClientSocket clientSocket = new ClientSocket();
    private TreeView treeView;
    private StringProperty timerText = new SimpleStringProperty();
    private int remainingSeconds;
    private Timeline timeline;

    public void setTimerText(int remainingSeconds) {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        this.timerText.set(String.format("%02d:%02d", minutes, seconds));
    }

    public String getTimerText() {
        return timerText.get();
    }

    public StringProperty timerTextProperty() {
        return timerText;
    }

    public void setTimerText(String timerText) {
        this.timerText.set(timerText);
    }

    public int getRemainingSeconds() {
        return remainingSeconds;
    }

    public void setRemainingSeconds(int remainingSeconds) {
        this.remainingSeconds = remainingSeconds;
    }

    public void tick() {
        remainingSeconds--;
    }

    private final class TextFieldTreeCell extends TreeCell<String> {
        private TextField textField;
        private ContextMenu contextMenu = new ContextMenu();

        public TextFieldTreeCell() {
            MenuItem addMenuItem = new MenuItem("Add Task");
            MenuItem removeMenuItem = new MenuItem("Remove Task");
            contextMenu.getItems().addAll(addMenuItem, removeMenuItem);
            addMenuItem.setOnAction(t -> {
                TreeItem newEmployee =
                        new TreeItem<>("New Task");
                getTreeItem().getChildren().add(newEmployee);
            });
            removeMenuItem.setOnAction(t -> {
                TreeItem removeEmployee = (TreeItem) treeView.getSelectionModel().getSelectedItem();
                removeEmployee.getParent().getChildren().remove(removeEmployee);
            });
        }

        @Override
        public void startEdit() {
            super.startEdit();

            if (textField == null) {
                createTextField();
            }
            setText(null);
            setGraphic(textField);
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            setText((String) getItem());
            setGraphic(getTreeItem().getGraphic());
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(getTreeItem().getGraphic());
                    setContextMenu(contextMenu);
                }
            }
        }



        private void createTextField() {
            textField = new TextField(getString());
            textField.setOnKeyReleased(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent t) {
                    if (t.getCode() == KeyCode.ENTER) {
                        commitEdit(textField.getText());
                    } else if (t.getCode() == KeyCode.ESCAPE) {
                        cancelEdit();
                    }
                }
            });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Time Task Manager");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(25);

        Text sceneTitle = new Text("Time Task Manager");
        sceneTitle.setId("task-title");
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label password = new Label("Password:");
        grid.add(password, 0, 2);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button register = new Button("Register");
        Button logIn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(register);
        hbBtn.getChildren().add(logIn);
        grid.add(hbBtn, 1, 3);

        Text hint = new Text();
        hint.setId("warning");
        HBox hbMessage = new HBox(10);
        hbMessage.setAlignment(Pos.BOTTOM_RIGHT);
        hbMessage.getChildren().add(hint);
        grid.add(hbMessage, 0, 4, 2, 1);


        BorderPane pane = new BorderPane();

        HBox header = new HBox();
        header.setSpacing(25);
        header.setId("header");
        ToggleButton play = new ToggleButton();
        play.setId("play");
        Label timer = new Label();
        timer.setId("timer");
        setRemainingSeconds(600);
        setTimerText(getRemainingSeconds());
        timer.setText(getTimerText());
        play.setSelected(false);
        header.getChildren().addAll(play, timer);

        VBox mainSection = new VBox();
        mainSection.setId("main");
        mainSection.setSpacing(8);
        TreeItem<String> rootNode =
                new TreeItem<>("My tasks");
        rootNode.setExpanded(true);

        treeView = new TreeView<>(rootNode);
        treeView.setEditable(true);
        treeView.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
            @Override
            public TreeCell<String> call(TreeView<String> p) {
                return new TextFieldTreeCell();
            }
        });
        mainSection.getChildren().add(treeView);

        VBox sideBar = new VBox();
        sideBar.setId("sidebar");
        sideBar.setSpacing(12);
        Button button1 = new Button("Add");
        Button button2 = new Button("Remove");
        Button button3 = new Button("Complete");
        Button button4 = new Button("Comment");
        sideBar.getChildren().addAll(button1, button2, button3, button4);

        VBox leftBar = new VBox();
        leftBar.setId("leftbar");

        HBox footer = new HBox();
        footer.setId("footer");

        pane.setTop(header);
        pane.setCenter(mainSection);
        pane.setRight(sideBar);
        pane.setBottom(footer);
        pane.setLeft(leftBar);

        Scene loginScene = new Scene(grid, 375, 275);
        primaryStage.setScene(loginScene);
        loginScene.getStylesheets().add(
                MainMenu.class.getResource("/css/Login.css").toExternalForm());
        primaryStage.getIcons().add(
                new Image(MainMenu.class.getResourceAsStream("/icons/TimeTaskIcon.png")));

        Scene mainScene = new Scene(pane, 800, 600);
        mainScene.getStylesheets().add(
                MainMenu.class.getResource("/css/Main.css").toExternalForm());

        primaryStage.setScene(loginScene);
        primaryStage.show();

        play.setOnAction(e -> {
            if (play.getId().equals("play")) {
                play.setId("pause");
                timeline = new Timeline();
                timeline.setCycleCount(getRemainingSeconds());
                timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), ae -> {
                    tick();
                    setTimerText(getRemainingSeconds());
                    timer.setText(getTimerText());
                }));
                timeline.play();
            } else {
                play.setId("play");
                timeline.pause();
            }
        });

        register.setOnAction(e -> {
            if(userTextField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()) {
                hint.setText("You need to enter all fields");
            } else {
                Message responseMessage = clientSocket.sendMessage(
                        userTextField.getText(),passwordField.getText(),
                        MessageType.CREATE_MESSAGE, "register"
                );

                if (responseMessage.getMessageType().equals(MessageType.FAIL_MESSAGE)) {
                    hint.setText("Already registered");
                } else if (responseMessage.getMessageType().equals(MessageType.RESPONSE_MESSAGE)) {
                    hint.setId("success");
                    hint.setText("Successfully registered");
                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(1000),
                            ae -> primaryStage.setScene(mainScene)));
                    timeline.play();
                }
            }
        });

        logIn.setOnAction(e -> {
            if(userTextField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty()) {
                hint.setText("You need to enter all fields");
            } else {
                Message responseMessage = clientSocket.sendMessage(
                        userTextField.getText(),passwordField.getText(),
                        MessageType.LOGIN_MESSAGE, "log in"
                );

                if (responseMessage.getMessageType().equals(MessageType.FAIL_MESSAGE)) {
                    hint.setText("Wrong username or password");
                } else if (responseMessage.getMessageType().equals(MessageType.RESPONSE_MESSAGE)) {
                    hint.setId("success");
                    hint.setText("Logged in successfully");

                    Timeline timeline = new Timeline(new KeyFrame(
                            Duration.millis(1000),
                            ae -> primaryStage.setScene(mainScene)));
                    timeline.play();
                }
            }
        });
    }
}
