package Client;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
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
            NodeList nList = doc.getElementsByTagName("deposit");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                String customerNumber = null;
                try {
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        customerNumber = eElement.getElementsByTagName("customerNumber").item(0).getTextContent();
                        String depositType = eElement.getElementsByTagName("depositType").item(0).getTextContent();
                        BigDecimal depositBalance = new BigDecimal(eElement.getElementsByTagName("depositBalance").item(0).getTextContent());
                        BigDecimal durationInDays = new BigDecimal(eElement.getElementsByTagName("durationInDays").item(0).getTextContent());
                        Deposit deposit = new Deposit(customerNumber, depositType, depositBalance, durationInDays);
                        depositArray.add(deposit);

                    }
                } catch (IncorrectBalanceValueException e) {
                    System.out.println(e.getMessage());
                } catch (IncorrectDaysValueException e) {
                    System.out.println(e.getMessage());
                } catch (ClassNotFoundException e) {
                    System.out.println("For customer: " + customerNumber + " --> Deposit Balance value is not correct");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}