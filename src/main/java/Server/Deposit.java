package Server;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class Deposit {
    private String customerName;
    private String depositID;
    private int initialBalance;
    private int upperBound;

    public Deposit(String customerName, String depositID, int initialBalance, int upperBound) {
        this.customerName = customerName;
        this.depositID = depositID;
        this.initialBalance = initialBalance;
        this.upperBound = upperBound;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDepositID() {
        return depositID;
    }

    public int getInitialBalance() {
        return initialBalance;
    }

    public int getUpperBound() {
        return upperBound;
    }
}
