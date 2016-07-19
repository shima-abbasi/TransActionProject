package Client;

import java.io.DataInputStream;
import java.net.Socket;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Client {
    public static void main(String[] args) {
        TerminalInfo terminalInfo= null;
        TerminalParse terminalParse = new TerminalParse();
        terminalParse.xmlParseFunction();
        try {
            Socket socket = new Socket("localhost",Integer.parseInt(terminalParse.getServerPort()));
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String msg = dis.readUTF();
            System.out.println(msg);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
