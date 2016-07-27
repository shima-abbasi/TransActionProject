package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client implements Runnable {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Client()).start();
        }
    }

    @Override
    public void run() {
        TerminalParse terminalParse = new TerminalParse();
        terminalParse.xmlParseFunction();
        ArrayList<Transaction> transactionArray = terminalParse.getTransactionArray();
        try {
            Socket socket = new Socket(terminalParse.getServerIP(), Integer.parseInt(terminalParse.getServerPort()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(transactionArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                System.out.println(objectInputStream.readObject());
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
