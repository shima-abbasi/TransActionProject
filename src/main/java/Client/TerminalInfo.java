package Client;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TerminalInfo {
    private String serverIP;
    private String serverPort;

    public TerminalInfo(String terminalID, String terminalType) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public TerminalInfo() {
    }

    public String getServerIP() {
        return serverIP;
    }

    public String getServerPort() {
        return serverPort;
    }

}
