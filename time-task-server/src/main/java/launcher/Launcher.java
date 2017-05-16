package launcher;

import application.ApplicationContext;
import network.SocketListener;

import java.io.IOException;

public class Launcher {
    public static void main(String[] args) throws IOException {
        ApplicationContext.init();

        SocketListener socketListener = new SocketListener();
        socketListener.start();
    }
}
