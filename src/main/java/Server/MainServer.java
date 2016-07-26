package Server;
import Client.Transaction;
import Server.Exceptions.InitialBalanceLimitationException;
import Server.Exceptions.NotFoundDeposit;
import Server.Exceptions.UpperBoundLimitationException;

import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MainServer {
    static DepositParse depositParse = new DepositParse();
    static Deposit deposit = new Deposit();
    static ArrayList<Deposit> depositArray;

    public static void main(String[] args) {
        MainServer mainServer = new MainServer();
        depositParse.jsonParserFunction();
        depositArray = depositParse.getDepositArray();
        mainServer.runServer();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());
            System.out.println("Server is up...");
            Socket socket = serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ArrayList<Transaction> transactions = (ArrayList<Transaction>) objectInputStream.readObject();
            for (Transaction transaction : transactions) {
                doTransAction(transaction);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public BigDecimal doTransAction(Transaction transaction) {
        BigDecimal newBalance = null;
        try {
            String type = transaction.getTransactionType();
            Deposit deposit1 = deposit.validation(transaction, depositArray);
            if (type.equalsIgnoreCase("deposit")) {
                newBalance = deposit.deposit(transaction.getTransactionAmount(), deposit1.getInitialBalance() , deposit1.getUpperBound());
            } else if (type.equalsIgnoreCase("withdraw")) {
                newBalance = deposit.withDraw(transaction.getTransactionAmount(), deposit1.getInitialBalance() , deposit1.getUpperBound());
            }
            System.out.println(newBalance);
        } catch (NotFoundDeposit e) {
            e.printStackTrace();
        } catch (UpperBoundLimitationException e) {
            e.printStackTrace();
        } catch (InitialBalanceLimitationException e) {
            e.printStackTrace();
        }
        return newBalance;
    }
}
