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
        ServerInfo serverInfo = new ServerInfo();
        try {
            // read the json file
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);
            // get a String from the JSON object
            String serverPort = (String) jsonObject.get("port");
            serverInfo.setServerPort(serverPort);
            JSONArray deposits =(JSONArray)jsonObject.get("deposits");
            for(Object deposit : deposits) {
                JSONObject jsonObject1 = (JSONObject)deposit;
                String customerName = (String) ((JSONObject) deposit).get("customer");
                String customerID = (String) ((JSONObject) deposit).get("id");
                int initialBalance = Integer.parseInt(String.valueOf((JSONObject)((JSONObject) deposit).get("initialBalance")));
                int upperBound = Integer.parseInt(String.valueOf((JSONObject)((JSONObject) deposit).get("upperBound")));
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
