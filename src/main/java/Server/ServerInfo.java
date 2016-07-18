package Server;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class ServerInfo {
    private int serverPort;

    public ServerInfo(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
}
