package Server;

import Client.Transaction;
import Server.Exceptions.InitialBalanceLimitationException;
import Server.Exceptions.NotFoundDeposit;
import Server.Exceptions.UpperBoundLimitationException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {
    static DepositParse depositParse = new DepositParse();
    static Deposit deposit = new Deposit();
    static ArrayList<Deposit> depositArray;
    ServerSocket serverSocket;
    Socket socket;
    ObjectOutputStream objectOutputStream;

    public static void main(String[] args) {
        MainServer mainServer = new MainServer();

        depositParse.jsonParserFunction();
        depositArray = depositParse.getDepositArray();
        mainServer.runServer();
    }

    public void runServer() {
        try {
            serverSocket = new ServerSocket(depositParse.getServerPort());
            System.out.println("Server is up...");
            socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ArrayList<Transaction> transactions = (ArrayList<Transaction>) objectInputStream.readObject();
            for (Transaction transaction : transactions) {
                doTransaction(transaction);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void doTransaction(Transaction transaction) throws IOException {
        BigDecimal newBalance = null;
        try {
            String type = transaction.getTransactionType();
            Deposit deposit1 = deposit.checkValidation(transaction, depositArray);
            if (type.equalsIgnoreCase("deposit")) {
                newBalance = deposit.deposit(transaction.getTransactionAmount(), deposit1.getInitialBalance(), deposit1.getUpperBound());
            } else if (type.equalsIgnoreCase("withdraw")) {
                newBalance = deposit.withDraw(transaction.getTransactionAmount(), deposit1.getInitialBalance(), deposit1.getUpperBound());
            }
            deposit1.setInitialBalance(newBalance);
            objectOutputStream.writeObject("Transaction was successful .New initial balance is " + newBalance);

        } catch (NotFoundDeposit e) {
            objectOutputStream.writeObject(e.getMessage());
        } catch (UpperBoundLimitationException e) {
            objectOutputStream.writeObject(e.getMessage());
        } catch (InitialBalanceLimitationException e) {
            objectOutputStream.writeObject(e.getMessage());
        }
    }
}
