package Client;

import Server.ServerInfo;

import java.io.DataInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Client {
    public static void main(String[] args) {
        ServerInfo server = new ServerInfo();
        TerminalInfo terminal = new TerminalInfo();
        try {
            Socket socket = new Socket(terminal.getServerIP(),Integer.parseInt(terminal.getServerPort()));
            OutputStream out = socket.getOutputStream();
            Formatter formatter = new Formatter() {
                @Override
                public String format(LogRecord record) {
                    return null;
                }
            }
            Socket s = ss.accept();//establishes connection
            DataInputStream dis = new DataInputStream(s.getInputStream());
            String str = (String) dis.readUTF();
            System.out.println("message= " + str);
            ss.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
