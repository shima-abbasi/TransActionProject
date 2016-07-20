package Client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Client {

    public static void main(String[] args) {

        TerminalInfo terminalInfo = null;
        TerminalParse terminalParse = new TerminalParse();
        ArrayList<String> transActionArray = terminalParse.getTransActionArray();
        terminalParse.xmlParseFunction();

        try {
            Socket socket = new Socket("localhost", Integer.parseInt(terminalParse.getServerPort()));
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(terminalParse.transActionArray);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
