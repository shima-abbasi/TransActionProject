package Server;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Server {
    public static void main(String[] args) {
        ServerInfo serverInfo = null;
        try {
            ServerSocket serverSocket = new ServerSocket(serverInfo.getServerPort());
            Socket socket = serverSocket.accept();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
