package Client;

import java.io.Serializable;
import java.math.BigDecimal;

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

    public TransAction() {
    }

    public TransAction(String id, String transactionType, String transactionAmount, String depositID) {
        this.id = id;
        this.transactionType = transactionType;
        this.transactionAmount = transactionAmount;
        this.depositID = depositID;
    }

    public BigDecimal withDraw(BigDecimal amount, BigDecimal initialBalance) {
        return initialBalance.subtract(amount);
    }

    public BigDecimal deposit(BigDecimal amount, BigDecimal initialBalance) {
        return initialBalance.add(amount);
    }
}
