package Client;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TerminalInfo {
    private String terminalID;
    private String terminalType;
    private String serverIP;
    private String serverPort;

    public TerminalInfo(String terminalID, String terminalType, String serverIP, String serverPort) {
        this.terminalID = terminalID;
        this.terminalType = terminalType;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
}
