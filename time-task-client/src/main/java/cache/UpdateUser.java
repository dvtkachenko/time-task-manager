package cache;

import entity.User;
import network.ClientSocket;
import network.Synchronization;

import javax.jws.soap.SOAPBinding;

/**
 * Created by Andrey on 12.05.2017.
 */
public class UpdateUser {
    public static void main(String[] args) {

        Synchronization synchronization = new Synchronization();
        synchronization.run();

    }
}
