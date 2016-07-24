package Server;

import Client.TransAction;
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
    DepositParse depositParse;

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

    public Deposit validation(TransAction transAction) throws NotFoundDeposit {
        Deposit deposit = findDeposit(transAction.getDepositID());
        return deposit;
    }

    public Deposit findDeposit(String depositID) throws NotFoundDeposit {
        ArrayList<Deposit> depositArray = depositParse.getDepositArray();
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
