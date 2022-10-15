import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String enabled;
    private static String fileName;
    private static String format;

    private static void node(Document doc, String name) {
        NodeList nodeList = doc.getElementsByTagName(name);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node_ = nodeList.item(i);
            if (Node.ELEMENT_NODE == node_.getNodeType()) {
                Element element = (Element) node_;
                enabled = element.getElementsByTagName("enabled").item(0).getTextContent();
                fileName = element.getElementsByTagName("fileName").item(0).getTextContent();
                if (!name.equals("log")) {
                    format = element.getElementsByTagName("format").item(0).getTextContent();
                }
            }
        }
    }
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));

        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        ClientLog clientLog = new ClientLog();
        Basket basket = new Basket(products, prices);
        Scanner scanner = new Scanner(System.in);


        node(doc, "load");
        if (enabled.equals("true")) {
            File textFile = new File(fileName+"."+format);
            if (textFile.exists()) {
                if (format.equals("json")) {
                    Basket.loadFromJSONFile(textFile);
                } else {
                    Basket.loadFromTxtFile(textFile);
                }
            } else {
                basket.printForBuy();
            }
        } else {
            basket.printForBuy();
        }


        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                basket.printCart();
                break;
            }

            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Нужно ввести два числа на одной строке");
                    continue;
                }

                int product = Integer.parseInt(parts[0]);

                if ((0 >= product) || (product >= products.length + 1)) {
                    System.out.println("В параметре надо указать коректный товар " + product);
                    continue;
                }

                product--;

                int pricesOne = Integer.parseInt(parts[1]);
                if (pricesOne <= 0) {
                    System.out.println("Введите положительное число " + pricesOne);
                    continue;
                }

                node(doc, "save");
                if (enabled.equals("true")) {
                    File textFile = new File(fileName+"."+format);
                    basket.addToCart(product, pricesOne);
                    if (format.equals("json")) {
                        basket.saveJSON(textFile);
                    } else {
                        basket.saveTxt(textFile);
                    }
                } else {basket.addToCart(product, pricesOne);}

                node(doc, "log");
                if (enabled.equals("true")) {
                    File textFile = new File(fileName);
                    clientLog.log(product, pricesOne);
                    clientLog.exportAsCSV(textFile);
                }
            } catch (
                    NumberFormatException e) {
                System.out.println("Нужно ввести два числа, а вы ввели " + input);
                continue;

            }

        }
    }
}






