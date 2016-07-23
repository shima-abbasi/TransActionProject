package Client;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TransAction implements Serializable {
    private int id;
    private String transactionType;
    private BigDecimal transactionAmount;
    private String depositID;

    public int getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public String getDepositID() {
        return depositID;
    }

    public TransAction() {
    }

    public TransAction(int id, String transactionType, BigDecimal transactionAmount, String depositID) {
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
