package Server;

import Client.TransAction;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Server {

    static DepositParse depositParse;

    public static void main(String[] args) {
        depositParse = new DepositParse();
        depositParse.jsonParserFunction();
        ArrayList<Deposit> depositArray = depositParse.getDepositArray();
        TransAction transAction = new Server().runServer();
        System.out.println(transAction.getId());
    }

    public TransAction runServer() {

        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());

            System.out.println("Server is up...");
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    return  (TransAction) objectInputStream.readObject();
                } finally {
                    socket.close();
                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}
