package network;

import entity.Task;
import entity.User;
import message.Message;
import message.MessageType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    public Message sendMessage(String username, String password, MessageType type, String userMessage) {
        Message responseMessage = null;
        try (
                Socket socket = new Socket("localhost", 5555);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            User user = new User(username, password);
            Message message = new Message(type, userMessage, user);

            oos.writeObject(message);
            oos.flush();

            responseMessage = (Message) ois.readObject();
            System.out.println(responseMessage);
            for (Task task: responseMessage.getUser().getTaskList()) {
                System.out.println(task.getSubTaskList());
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return responseMessage;
    }

    public Message sendMessage(User user, MessageType type) {
        Message responseMessage = null;
        try (
                Socket socket = new Socket("localhost", 5555);
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())
        ) {
            Message message = new Message(type, null, user);

            oos.writeObject(message);
            oos.flush();
            responseMessage = (Message) ois.readObject();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return responseMessage;
    }
}
