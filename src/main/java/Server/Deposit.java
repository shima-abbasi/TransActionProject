package Server;

import Client.TransAction;
import Server.Exceptions.NotFoundDeposit;

import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class Deposit {
    private String customerName;
    private String depositID;
    private int initialBalance;
    private int upperBound;
    DepositParse depositParse;

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

    public boolean validation(TransAction transAction) {

        try{
            Deposit deposit = findDeposit(transAction.getDepositID());
        }catch ( NotFoundDeposit e){
            System.out.println("Your Deposit ID not exist");

        }
    }
    public Deposit findDeposit(String depositID) throws NotFoundDeposit{
        ArrayList <Deposit> depositArray = depositParse.getDepositArray();
        for(Deposit deposit : depositArray)
        {
            if(deposit.getDepositID().equals(depositID)) {
                return deposit;
            }
        }

        return null;
    }
}
