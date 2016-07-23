package Client;

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
        ArrayList<TransAction> transActionArray = terminalParse.getTransActionArray();
        terminalParse.xmlParseFunction();

        try {
            Socket socket = new Socket("localhost", Integer.parseInt(terminalParse.getServerPort()));
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            for(TransAction transAction : transActionArray){
                objectOutputStream.writeObject(transAction);
                objectOutputStream.flush();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
