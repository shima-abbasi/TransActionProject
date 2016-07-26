package Client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Client {

    public static void main(String[] args) {

        // TerminalInfo terminalInfo = null;
        TerminalParse terminalParse = new TerminalParse();
        terminalParse.xmlParseFunction();
        ArrayList<TransAction> transActionArray = terminalParse.getTransActionArray();


        try {
            Socket socket = new Socket("localhost", Integer.parseInt(terminalParse.getServerPort()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(transActionArray);
        } catch (UnknownHostException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
