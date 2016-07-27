package Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MultiThreadClient {
    static String terminal;

    public MultiThreadClient(String terminal) {
        this.terminal = terminal;
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new MultiThreadClient("terminal0.xml");
                new MultiThreadClient("terminal1.xml");
                TerminalParse terminalParse = new TerminalParse(terminal);
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
        }).start();
    }
}
