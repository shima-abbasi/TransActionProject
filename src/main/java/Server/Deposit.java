package Server;

import Client.Transaction;
import Server.Exceptions.NotFoundDeposit;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class Deposit {
    private String customerName;
    private String depositID;
    private BigDecimal initialBalance;
    private BigDecimal upperBound;

    public Deposit() {
    }

    public Deposit(String customerName, String depositID, BigDecimal initialBalance, BigDecimal upperBound) {
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

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public Deposit validation(Transaction transaction, ArrayList<Deposit> depositArray) throws NotFoundDeposit {
        Deposit deposit = findDeposit(transaction.getDepositID(), depositArray);
        return deposit;
    }

    public Deposit findDeposit(String depositID, ArrayList<Deposit> depositArray) throws NotFoundDeposit {
        for (Deposit deposit : depositArray) {
            if (deposit.getDepositID().equals(depositID)) {
                return deposit;
            }
        }
        throw new NotFoundDeposit("This deposit doesn't exist");
    }

    public BigDecimal withDraw(BigDecimal amount, BigDecimal initialBalance) {
        return initialBalance.subtract(amount);
    }

    public BigDecimal deposit(BigDecimal amount, BigDecimal initialBalance) {
        return initialBalance.add(amount);
    }

}
