package Client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by Shima Abbasi on 7/18/2016.
 */
public class TerminalParse {
    public void xmlParseFunction() {
        ArrayList<TransAction> transActionArray = new ArrayList<TransAction>();
        try {
            //----loading file-------------------
            File fXmlFile = new File("src/main/resources/terminal.XML");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            //-----------------------------------
            doc.getDocumentElement().normalize();
            //-----------------------------------
            Node node = (Node) doc.getElementsByTagName("terminal");
            String terminalID = String.valueOf(node.getAttributes().item(0));

            NodeList nList = doc.getElementsByTagName("transactions");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                     //   String terminalID = eElement.getAttribute("id");
                        String terminalType = eElement.getAttribute("type");
                        TransAction transAction = new TransAction();
                        transActionArray.add(transAction);

                    }
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}