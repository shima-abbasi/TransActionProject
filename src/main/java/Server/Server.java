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
        DepositParse depositParse = new DepositParse();
        depositParse.jsonParserFunction();
        ArrayList<Deposit> depositArray = depositParse.getDepositArray();

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
