package launcher;

import application.ApplicationContext;
import network.SocketListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class Launcher {

    private static final Logger infoLogger = LogManager.getLogger("infoLogger");

    public static void main(String[] args) throws IOException {

        infoLogger.info("Application started");

        ApplicationContext.init();

        SocketListener socketListener = new SocketListener();
        socketListener.start();

    }
}
