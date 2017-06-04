package launcher;

import application.ApplicationContext;
import network.SocketListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application starter. Initialize ApplicationContext and start SocketListener.
 */
public class Launcher {
    private static final Logger infoLogger = LogManager.getLogger("infoLogger");

    public static void main(String[] args) {

        infoLogger.info("Application started");

        ApplicationContext.init();

        SocketListener socketListener = new SocketListener();
        socketListener.start();

    }
}
