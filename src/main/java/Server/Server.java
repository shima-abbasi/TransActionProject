package Server;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class Server {
    public static void main(String[] args) {
        ArrayList<Deposit> depositArray = new ArrayList<Deposit>();
        DepositParse depositParse = new DepositParse();
        depositArray = depositParse.jsonParserFunction();

        try {
            ServerSocket serverSocket = new ServerSocket(depositParse.getServerPort());
            Socket socket = serverSocket.accept();
            serverSocket.setSoTimeout(10000);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF("Heyyo");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
