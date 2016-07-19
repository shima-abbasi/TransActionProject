package Client;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TransAction {
    private String transactionID;
    private String transactionType;
    private String transactionAmount;
    private String depositID;

    public TransAction(String transactionID, String transactionType, String transactionAmount, String depositID) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.depositID = depositID;
    }

    public TransAction() {

    }
}
