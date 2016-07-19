package Server;

/**
 * Created by Shima Abbasi on 7/19/2016.
 */
public class RunServer {
    public static void main(String[] args) {
        DepositParse depositParser = new DepositParse();
        depositParser.jsonParserFunction();
    }
}
