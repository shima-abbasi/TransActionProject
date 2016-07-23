package Server;

import Client.TransAction;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Server {
    static DepositParse depositParse = new DepositParse();
    static ArrayList<Deposit> depositArray = depositParse.getDepositArray();

    public static void main(String[] args) {
        depositParse.jsonParserFunction();
        TransAction transAction = new Server().runServer();
        new Server().validation(transAction.getId(), transAction.getTransactionType(), transAction.getTransactionAmount(), transAction.getDepositID());
    }
    public TransAction runServer() {

        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());

            System.out.println("Server is up...");
            while (true) {
                Socket socket = serverSocket.accept();
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    return (TransAction) objectInputStream.readObject();
                } finally {
                    socket.close();
                }
            }


        } catch (Exception e) {
            System.out.println(e);
        }


        return null;
    }

    public boolean validation( int id, String type, BigDecimal amount , String depositId) {
        if (depositId.equalsIgnoreCase();

        return true;
    }

    public void transAction() {
    }

    public void findDeposit() {
    }
}
