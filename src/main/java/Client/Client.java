package Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {

    public static void main(String[] args) {
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
