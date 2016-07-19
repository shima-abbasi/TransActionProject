package Client;

import Server.ServerInfo;

import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Client {
    public static void main(String[] args) {
        ServerInfo server = null;
        TerminalInfo terminal = new TerminalInfo();
        try {
            Socket socket = new Socket("localhost",Integer.parseInt(terminal.getServerPort()));
            OutputStream out = socket.getOutputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
