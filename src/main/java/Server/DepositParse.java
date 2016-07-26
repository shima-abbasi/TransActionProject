package Server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */

public class DepositParse {

    int serverPort;
    String logFilePath;
    ArrayList<Deposit> depositArray = new ArrayList<Deposit>();

    public void setDepositArray(ArrayList<Deposit> depositArray) {
        this.depositArray = depositArray;
    }

    private final String filePath = "src/main/resources/core.json";


    public int getServerPort() {
        return serverPort;
    }

    public ArrayList<Deposit> getDepositArray() {
        return depositArray;
    }

    public void jsonParserFunction() {

        try {
            // read the json file
            FileReader reader = new FileReader(filePath);

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

            serverPort = Integer.parseInt(jsonObject.get("port").toString());
            ServerInfo serverInfo = new ServerInfo();
            serverInfo.setServerPort(serverPort);

            JSONArray deposits = (JSONArray) jsonObject.get("deposits");


            for (int i = 0; i < deposits.size(); i++) {
                NumberFormat format = NumberFormat.getCurrencyInstance();
                Number number = format.parse("$123,456.78");
                JSONObject jsonDeposit = (JSONObject) deposits.get(i);
                String customerName = (String) jsonDeposit.get("customer");
                String depositID = (String) jsonDeposit.get("id");
                BigDecimal initialBalance = new BigDecimal((jsonDeposit.get("initialBalance").toString()).replaceAll("," , ""));
                BigDecimal upperBound = new BigDecimal(jsonDeposit.get("upperBound").toString().replaceAll("," , ""));
                depositArray.add(new Deposit(customerName, depositID, initialBalance, upperBound));
            }
          //  logFilePath = "src\\main\\resources\\" + (String) jsonObject.get("outLog");

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
    }

}
