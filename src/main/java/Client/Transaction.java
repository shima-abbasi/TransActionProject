package Client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Shima Abbasi on 7/26/2016.
 */
public class Transaction implements Serializable {
    private int id;
    private String transactionType;
    private BigDecimal transactionAmount;
    private String depositID;

    public Transaction() {
    }

    public Transaction(int id, String transactionType, BigDecimal transactionAmount, String depositID) {
        this.id = id;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.depositID = depositID;
    }

    public int getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getDepositID() {
        return depositID;
    }

    @Override
    public String toString() {
        return "TransAction{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", depositID='" + depositID + '\'' +
                '}';
    }


}
