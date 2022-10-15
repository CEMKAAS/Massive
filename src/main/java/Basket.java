import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;

    protected int[] quantity;
    protected int[] quantityProduct;


    public Basket(String[] products, int[] prices) throws FileNotFoundException {
        this.prices = prices;
        this.products = products;
        this.quantity = new int[products.length];
        this.quantityProduct = new int[products.length];
    }

    public void setQuantity(int[] quantity) {
        this.quantity = quantity;
    }

    public void setQuantityProduct(int[] quantityProduct) {
        this.quantityProduct = quantityProduct;
    }

    public void addToCart(int productNum, int amount) {
        if (productNum < (products.length + 1) && productNum >= 0) {
            quantity[productNum] += amount;
            int currentPrice = prices[productNum];
            quantityProduct[productNum] = quantity[productNum] * currentPrice;
        } else {
            System.out.println("Такого товара нет");
        }
    }

    public void saveJSON(File textFile) throws IOException {
        JSONObject obj = new JSONObject();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        JSONArray listOne = new JSONArray();
        JSONArray listTwo = new JSONArray();
        JSONArray lisThree = new JSONArray();

        lisThree.add(gson.toJson(products));
        obj.put("product", lisThree);

        listOne.add(gson.toJson(quantity));
        obj.put("productNum", listOne);

        listTwo.add(gson.toJson(quantityProduct));
        obj.put("amount", listTwo);

        try (FileWriter file = new FileWriter(textFile)) {
            file.write(obj.toJSONString());
            file.flush();
        }
    }


    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (String e : products) {
                out.print(e + " ");
            }
            out.print("\n");
            for (int e : quantity)
                out.print(e + " ");
            out.print("\n");
            for (int a : quantityProduct)
                out.print(a + " ");

        }
    }

    public static Basket loadFromJSONFile(File textFile) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        String as = null;
        String as2 = null;
        String as3 = null;
        try {
            Object obj = parser.parse(new FileReader(textFile));
            JSONObject jsonObject = (JSONObject) obj;

            JSONArray amount = (JSONArray) jsonObject.get("amount");
            JSONArray productNum = (JSONArray) jsonObject.get("productNum");
            JSONArray product = (JSONArray) jsonObject.get("product");

            for (Object am : amount) {
                as = am.toString();
            }
            for (Object am : productNum) {
                as2 = am.toString();
            }
            for (Object am : product) {
                as3 = am.toString();
            }

            String[] productJSON = as3.replace("]", "").replace("[", "").split(",");
            String[] quantityJSON = as2.replace("]", "").replace("[", "").split(",");
            String[] quantityProductJSON = as.replace("]", "").replace("[", "").split(",");

            int[] quantity = new int[quantityJSON.length];
            int[] quantityProduct = new int[quantityProductJSON.length];
            for (int i = 0; i < quantityJSON.length; i++) {
                quantity[i] = Integer.parseInt(quantityJSON[i]);
                quantityProduct[i] = Integer.parseInt(quantityProductJSON[i]);
            }
            Basket basket = new Basket(productJSON, quantityProduct);
            basket.setQuantity(quantity);
            basket.setQuantityProduct(quantityProduct);
            basket.printCart();
            return basket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        InputStream in = new FileInputStream(textFile);
        Scanner scanner1 = new Scanner(in);
        String[] productsTXT = scanner1.nextLine().split(" ");
        String[] quantityTXT = scanner1.nextLine().split(" ");
        String[] quantityProductTXT = scanner1.nextLine().split(" ");
        int[] quantity = new int[quantityTXT.length];
        int[] quantityProduct = new int[quantityProductTXT.length];
        for (int i = 0; i < quantityTXT.length; i++) {
            quantity[i] = Integer.parseInt(quantityTXT[i]);
            quantityProduct[i] = Integer.parseInt(quantityProductTXT[i]);
        }
        Basket basket = new Basket(productsTXT, quantityProduct);
        basket.setQuantity(quantity);
        basket.setQuantityProduct(quantityProduct);
        basket.printCart();
        return basket;

    }


    public void printCart() {
        StringBuilder print = new StringBuilder();
        print.append("Ваша корзина");
        print.append("\n");
        for (int i = 0; i < products.length; i++) {
            if (quantity[i] >= 1) {
                print.append(products[i] + " " + quantity[i] + " шт " + quantityProduct[i] + " руб в сумме ");
                print.append("\n");
            }
        }
        print.append("Итого: " + Arrays.stream(quantityProduct).sum() + " руб");
        System.out.println(print.toString());
    }

    protected void printForBuy() {
        System.out.println("Список возможных товаров для покупки: ");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб.");
        }
    }
}










