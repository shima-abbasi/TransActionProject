package Client;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TerminalParse {
    String serverPort;
    String serverIP;
    String filePath;
    Transaction transaction;
    ArrayList<Transaction> transactionArray = new ArrayList<Transaction>();

    public TerminalParse(String filePath) {
        this.filePath=filePath;

    }


    public ArrayList<Transaction> getTransactionArray() {
        return transactionArray;
    }

    public String getServerIP() {
        return serverIP;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void xmlParseFunction() {

        try {
            //----loading file-------------------
            File fXmlFile = new File("src/main/resources/" + filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            //-----------------------------------
            doc.getDocumentElement().normalize();
            //-----------------terminal------------------
            NodeList terminalNode = doc.getElementsByTagName("terminal");
            NamedNodeMap terminalAttributes = terminalNode.item(0).getAttributes();
            String terminalID = terminalAttributes.item(0).getNodeValue();
            String terminalType = terminalAttributes.item(1).getNodeValue();
            //----------------server----------------------
            NodeList serverNode = doc.getElementsByTagName("server");
            NamedNodeMap serverAttributes = serverNode.item(0).getAttributes();
            serverIP = serverAttributes.item(0).getNodeValue();
            serverPort = serverAttributes.item(1).getNodeValue();


            //--------------transaction--------------------
            NodeList transActionNode = doc.getElementsByTagName("transaction");
            for (int temp = 0; temp < transActionNode.getLength(); temp++) {
                Node nNode = transActionNode.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    NamedNodeMap transActionAttributes = transActionNode.item(temp).getAttributes();

                    int transActionID = Integer.parseInt(transActionAttributes.getNamedItem("id").getNodeValue());
                    String transActionType = transActionAttributes.getNamedItem("type").getNodeValue();
                    BigDecimal transActionAmount = new BigDecimal(transActionAttributes.getNamedItem("amount").getNodeValue().replaceAll("," , ""));
                    String depositID = transActionAttributes.getNamedItem("deposit").getNodeValue();
                    transactionArray.add(new Transaction(transActionID, transActionType, transActionAmount, depositID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}