package network;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * At start opens ServerSocket and delegate incoming connections to RequestHandler,
 * which executing in separate thread from thread pool.
 */
public class SocketListener extends Thread {
    private static final Logger errorLogger = LogManager.getLogger("errorLogger");

    @Override
    public void run() {
        ExecutorService pool = new ThreadPoolExecutor(
                8, 64, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256));
        try (ServerSocket listener = new ServerSocket(5555)) {
            while (true) {
                Socket socket = listener.accept();
                RequestHandler requestHandler = new RequestHandler(socket);
                pool.submit(requestHandler);
            }
        } catch (IOException e) {
            errorLogger.error("Socket connection error.", e);
        }
    }
}
