package Server;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Server {
    DepositParse depositParse = new DepositParse();
    depositParse.jsonParserFunction();
    ArrayList<Deposit> depositArray = depositParse.getDepositArray();

    public static void main(String[] args) {
        new Server().runServer();
        try {

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            String s = dataInputStream.readUTF();
        } catch (Exception e) {
            System.out.println(e);
        }
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
