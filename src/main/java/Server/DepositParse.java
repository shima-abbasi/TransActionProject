package Server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */

public class DepositParse {
    ArrayList<Deposit> serverArray = new ArrayList<Deposit>();

    private final String filePath = "src/main/resources/core.json";

    public void jsonParserFunction() {

        try {
            // read the json file
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);


            int serverPort = Integer.parseInt(jsonObject.get("port").toString());
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setServerPort(serverPort);

            JSONArray deposits =(JSONArray)jsonObject.get("deposits");

            for(int i=0;i<deposits.size();i++){
                Deposit deposit =new Deposit();
                JSONObject jsonDeposit = (JSONObject) deposits.get(i);
                String customerName = (String) jsonDeposit.get("customer");
                deposit.setCustomerName(customerName);
                String depositID = (String)jsonDeposit.get("id");
                deposit.setDepositID(depositID);
                int initialBalance = Integer.parseInt(jsonDeposit.get("initialBalance").toString());
                deposit.setInitialBalance(initialBalance);
                int upperBound = Integer.parseInt(jsonDeposit.get("upperBound").toString());
                deposit.setUpperBound(upperBound);
                serverArray.add(new Deposit(customerName, depositID, initialBalance, upperBound));
            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

}
