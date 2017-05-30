package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketListener extends Thread {
    @Override
    public void run() {
        ExecutorService pool = new ThreadPoolExecutor(
                8, 64, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(256));
        try (ServerSocket listener = new ServerSocket(5555)) {
            while (true) {
                Socket socket = listener.accept();
                RequestHandlingThread thread = new RequestHandlingThread(socket);
                pool.submit(thread);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
