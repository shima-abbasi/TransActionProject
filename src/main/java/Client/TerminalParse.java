package Client;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TerminalParse {
    public void xmlParseFunction() {
        ArrayList<TerminalInfo> terminalInfoArray = new ArrayList<TerminalInfo>();
        ArrayList<TransAction> transActionArray = new ArrayList<TransAction>();
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
            String serverIP = serverAttributes.item(0).getNodeValue();
            String serverPort = serverAttributes.item(1).getNodeValue();

            terminalInfoArray.add(new TerminalInfo(terminalID, terminalType, serverIP, serverPort));

            //--------------transAction--------------------
            NodeList transActionNode = doc.getElementsByTagName("transaction");
            for (int temp = 0; temp < transActionNode.getLength(); temp++) {
                Node nNode = transActionNode.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    NamedNodeMap transActionAttributes = transActionNode.item(temp).getAttributes();

                    String transActionID = transActionAttributes.item(0).getNodeValue();
                    String transActionType = transActionAttributes.item(1).getNodeValue();
                    String transActionAmount = transActionAttributes.item(2).getNodeValue();
                    String depositID = transActionAttributes.item(3).getNodeValue();
                    transActionArray.add(new TransAction(transActionID, transActionType, transActionAmount, depositID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}