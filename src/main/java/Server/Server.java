package Server;

import Client.TransAction;
import Server.Exceptions.NotFoundDeposit;

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
    static Deposit deposit;
    static ArrayList<Deposit> depositArray;

    public static void main(String[] args) {
        depositParse.jsonParserFunction();
        depositArray = depositParse.getDepositArray();
        deposit= new Deposit();
        new Server().runServer();

    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());
            System.out.println("Server is up...");
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(1000000000);
            while (true) {
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    TransAction transAction = (TransAction) objectInputStream.readObject();
                    System.out.println(doTransAction(transAction));
                } finally {
                    socket.close();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public BigDecimal doTransAction(TransAction transAction){
        BigDecimal newBalance = null;
        try {
            Deposit deposit1 = deposit.validation(transAction);
            if (transAction.getTransactionType() == "deposit") {
                newBalance= deposit.deposit(transAction.getTransactionAmount() , deposit1.getInitialBalance());
            } else if (transAction.getTransactionType() == "withdraw") {
                newBalance =  deposit.withDraw(transAction.getTransactionAmount(), deposit1.getInitialBalance());
            }
        }catch ( NotFoundDeposit e){
            System.out.println("??");
        }
        return newBalance;
    }
}
