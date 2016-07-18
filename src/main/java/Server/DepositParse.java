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
    ArrayList<Deposit> depositArray = new ArrayList<Deposit>();
    private final String filePath = "src/main/resources/core.json";

    public void jsonParserFunction() {

        try {
            // read the json file
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
            // get a String from the JSON object
            int serverPort = Integer.parseInt(jsonObject.get("port").toString());
            ServerInfo serverInfo = new ServerInfo(serverPort);
            JSONArray deposits =(JSONArray)jsonObject.get("deposits");
            for(int i=0;i<deposits.size();i++){
                JSONObject jsonDeposit = (JSONObject) deposits.get(i);
                String customerName = (String)jsonDeposit.get("customer");
                String customerID = (String)jsonDeposit.get("id");
                int initialBalance = Integer.parseInt(jsonDeposit.get("initialBalance").toString());
                int upperBound = Integer.parseInt(jsonDeposit.get("upperBound").toString());
                depositArray.add(new Deposit(customerName, customerID, initialBalance, upperBound));
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
