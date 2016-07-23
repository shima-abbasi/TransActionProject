package Client;

import java.io.Serializable;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TransAction implements Serializable {
    private String id;
    private String transactionType;
    private String transactionAmount;
    private String depositID;

    public String getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public String getDepositID() {
        return depositID;
    }

    public TransAction(String id, String transactionType, String transactionAmount, String depositID) {
        this.id = id;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.depositID = depositID;
    }

    public TransAction() {

    }
}
