package Server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Server {
    static DepositParse depositParse ;
    public static void main(String[] args) {
        depositParse = new DepositParse();
        depositParse.jsonParserFunction();
        ArrayList<Deposit> depositArray = depositParse.getDepositArray();
        new Server().runServer();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());
            Socket socket = serverSocket.accept();
            System.out.println("Server is up...");
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(socket.getOutputStream());
            objectInputStream.readObject();
            serverSocket.setSoTimeout(10000);
            socket.close();


        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
