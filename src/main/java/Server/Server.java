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
    static ArrayList<Deposit> depositArray;

    public static void main(String[] args) {
        depositParse.jsonParserFunction();
        depositArray = depositParse.getDepositArray();
        new Server().runServer();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());
            System.out.println("Server is up...");
            Socket socket = serverSocket.accept();
            while (true) {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    TransAction transAction = (TransAction) objectInputStream.readObject();
                    doTransAction(transAction);
                } finally {
                    socket.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void doTransAction(TransAction transAction) {
        if (Validation(transAction) && transAction.getTransactionType() = "deposit") {
            transAction.deposit(transAction.getTransactionAmount() ,);


        } else if (Validation(transAction) && transAction.getTransactionType() = "withdraw") {
            transAction.withDraw(transAction.getTransactionAmount(),);
        }
    }
}
