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
