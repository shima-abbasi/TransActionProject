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

public class MultiThreadServer implements Runnable {

    static Deposit deposit = new Deposit();
    static DepositParse depositParse = new DepositParse();
    static ArrayList<Deposit> depositArray;
    ServerSocket serverSocket;
    Socket socket;
    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    //---------------------------------------------
    public MultiThreadServer(Socket socket) {
        this.socket = socket;
    }
    public MultiThreadServer() {
    }
    //----------------------------------------------
    public static void main(String[] args) {
        MultiThreadServer mainServer = new MultiThreadServer();
        depositParse.jsonParserFunction();
        depositArray = depositParse.getDepositArray();
        mainServer.runServer();
    }

    //--------------------------------------------------
    public void runServer() {
        try {
            serverSocket = new ServerSocket(depositParse.getServerPort());
            System.out.println("Server is up...");
            while (true) {
                socket = serverSocket.accept();
                System.out.println("Server is connected...");
                new Thread(new MultiThreadServer(socket)).start();

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    //------------------------------------------
    @Override
    public void run() {
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ArrayList<Transaction> transactions = (ArrayList<Transaction>) objectInputStream.readObject();
            for (Transaction transaction : transactions) {
                doTransaction(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

//-----------------------------------------------
    public void doTransaction(Transaction transaction) throws IOException {
        BigDecimal newBalance = BigDecimal.ZERO;
        try {
            String type = transaction.getTransactionType();
            Deposit deposit1 = deposit.checkValidation(transaction, depositArray);
            if (type.equalsIgnoreCase("deposit")) {
                newBalance = deposit.doDeposit(transaction.getTransactionAmount(), deposit1.getInitialBalance(), deposit1.getUpperBound());
            } else if (type.equalsIgnoreCase("withdraw")) {
                newBalance = deposit.doWithDraw(transaction.getTransactionAmount(), deposit1.getInitialBalance());
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
