package network;

import controller.CreateUserController;
import controller.LoginController;
import controller.UpdateController;
import entity.User;
import message.Message;
import message.MessageType;
import network.exception.WrongMessageFormatException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RequestHandlingThread extends Thread {
    private Socket socket;

    public RequestHandlingThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {

            Message message = (Message) ois.readObject();
            User user = null;
            MessageType messageType = MessageType.FAIL_MESSAGE;
            String messageString = null;

            switch (message.getMessageType()) {
                case LOGIN_MESSAGE:
                    System.out.println("Login message received");
                    LoginController loginController = new LoginController();
                    user = loginController.login(message.getUser());
                    if (user != null) {
                        messageType = MessageType.RESPONSE_MESSAGE;
                    } else {
                        messageString = "Wrong login or password";
                    }
                    break;
                case CREATE_MESSAGE:
                    System.out.println("Create message received");
                    CreateUserController createUserController = new CreateUserController();
                    user = createUserController.createUser(message.getUser());
                    if (user != null) {
                        messageType = MessageType.RESPONSE_MESSAGE;
                    } else {
                        messageString = "User exists";
                    }
                    break;
                case UPDATE_MESSAGE:
                    System.out.println("Update message received");
                    UpdateController updateController = new UpdateController();
                    user = updateController.updateUserTasks(message.getUser());
                    if (user != null) {
                        messageType = MessageType.UPDATE_SUCCESS_MESSAGE;
                    } else {
                        messageString = "Update failed";
                    }
                    break;
                default:
                    System.out.println("Unknown message received");
                    throw new WrongMessageFormatException("Message have wrong type");
            }

            Message responseMessage = new Message(messageType, messageString, user);
            oos.writeObject(responseMessage);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WrongMessageFormatException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}