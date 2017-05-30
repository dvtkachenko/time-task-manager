import application.ApplicationContext;
import entity.User;
import message.Message;
import message.MessageType;
import network.SocketListener;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ObjectOutputStream;
import java.net.Socket;

import static org.junit.Assert.*;

public class TestNetwork {
//    private static SocketListener socketListener;
//
//    @BeforeClass
//    public static void setUp() throws Exception {
//        ApplicationContext.init();
//        socketListener = new SocketListener();
//        socketListener.start();
//    }
//
//    @Test
//    public void testCreateUser() throws Exception {
//        Socket socket = new Socket("localhost", 5555);
//        ObjectOutputStream oos = new ObjectOutputStream((socket.getOutputStream()));
//        Message message = new Message(MessageType.CREATE_MESSAGE, null, new User("abc", "123"));
//
//        oos.writeObject(message);
//        Thread.sleep(100);
//        socket.close();
//
//        assertEquals(MessageType.CREATE_MESSAGE, message.getMessageType());
//    }
//
//    @Test
//    public void testLoginUser() throws Exception {
//        Socket socket = new Socket("localhost", 5555);
//        ObjectOutputStream oos = new ObjectOutputStream((socket.getOutputStream()));
//        Message message = new Message(MessageType.LOGIN_MESSAGE, null, new User("abc", "123"));
//
//        oos.writeObject(message);
//        Thread.sleep(100);
//        socket.close();
//
//        assertEquals(MessageType.LOGIN_MESSAGE, message.getMessageType());
//    }
//
//    @Test
//    public void testUpdateUser() throws Exception {
//        Socket socket = new Socket("localhost", 5555);
//        ObjectOutputStream oos = new ObjectOutputStream((socket.getOutputStream()));
//        Message message = new Message(MessageType.UPDATE_MESSAGE, null, new User("abc", "123"));
//
//        oos.writeObject(message);
//        Thread.sleep(100);
//        socket.close();
//
//        assertEquals(MessageType.UPDATE_MESSAGE, message.getMessageType());
//    }
//
//    @Test
//    public void testUserAuthentication() throws Exception {
//        Socket socket = new Socket("localhost", 5555);
//        ObjectOutputStream oos = new ObjectOutputStream((socket.getOutputStream()));
//        Message message = new Message(MessageType.CREATE_MESSAGE, null, new User("abc", "123"));
//
//        oos.writeObject(message);
//        Thread.sleep(100);
//        socket.close();
//
//        assertEquals("abc", message.getUser().getLogin());
//        assertEquals("123", message.getUser().getPassword());
//    }
//
//    @Test
//    public void testLoad() throws Exception {
//        for (int i = 0; i < 200; i++) {
//            Socket socket = new Socket("localhost", 5555);
//            ObjectOutputStream oos = new ObjectOutputStream((socket.getOutputStream()));
//            Message message = new Message(
//                    MessageType.CREATE_MESSAGE, null, new User("User" + i, "Pass" + i));
//
//            oos.writeObject(message);
//            Thread.sleep(100);
//            socket.close();
//
//
//            assertEquals("User" + i, message.getUser().getLogin());
//            assertEquals("Pass" + i, message.getUser().getPassword());
//        }
//    }
}
