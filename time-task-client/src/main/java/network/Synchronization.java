package network;

import entity.User;
import model.UserSerializable;

/**
 * Created by Andrey on 18.05.2017.
 */
public class Synchronization implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000 * 60 * 60); // one in hours
                UserSerializable userSerializable = new UserSerializable();
                User user = userSerializable.readUserOnDisc();
                ClientSocket socket = new ClientSocket();
                socket.sendMessage(user);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
