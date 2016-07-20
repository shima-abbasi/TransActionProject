package Client;

import java.io.*;
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
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            for( TransAction transAction : transActionArray){
                dataOutputStream.writeUTF(transAction.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
