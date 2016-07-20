package Client;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class  TerminalParse implements Serializable {
    String serverPort;
    String serverIP;
    TransAction transAction;
    ArrayList<String> transActionArray = new ArrayList<String>();

    public static void main(String[] args) {
        TerminalParse instance = new TerminalParse();
        instance.serverIP = "10.0.0.10";
        instance.serverPort = "1234";
        instance.transAction = null;

        try {
            ObjectOutputStream out = new ObjectOutputStream(System.out);
            out.writeObject(instance);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<String> getTransActionArray() {
        return transActionArray;
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
            File fXmlFile = new File("src/main/resources/terminal.XML");
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


            //--------------transAction--------------------
            NodeList transActionNode = doc.getElementsByTagName("transaction");
            for (int temp = 0; temp < transActionNode.getLength(); temp++) {
                Node nNode = transActionNode.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    NamedNodeMap transActionAttributes = transActionNode.item(temp).getAttributes();

                    String transActionID = transActionAttributes.getNamedItem("id").getNodeValue();
                    String transActionType = transActionAttributes.getNamedItem("type").getNodeValue();
                    String transActionAmount = transActionAttributes.getNamedItem("amount").getNodeValue();
                    String depositID = transActionAttributes.getNamedItem("deposit").getNodeValue();
                    transActionArray.add(new TransAction(transActionID, transActionType, transActionAmount, depositID).getString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}