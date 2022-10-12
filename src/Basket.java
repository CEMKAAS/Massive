import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket implements Serializable {
    private String[] products;
    private int[] prices;
    private int[] quantity;
    private int[] quantityProduct;


    public Basket (String[] products, int[] prices) throws FileNotFoundException {
        this.prices = prices;
        this.products = products;
        this.quantity= new int[prices.length];
        this.quantityProduct = new int[prices.length];
    }
    public void setCount(int[] quantity) {
        this.quantity = quantity;
    }
    public void setCount2(int[] quantityProduct) {
        this.quantityProduct = quantityProduct;
    }



   public void addToCart(int productNum, int amount){
       if (productNum < (products.length + 1) && productNum >= 0) {
           quantity[productNum] += amount;
           int currentPrice = prices[productNum];
           quantityProduct[productNum] = quantity[productNum] * currentPrice;
       } else {
           System.out.println("Такого товара нет");
       }
   }

    protected void printCart () {

            StringBuilder print = new StringBuilder();
            print.append("Ваша корзина");
            print.append("\n");
            for (int i = 0; i < products.length; i++) {
                if (quantity[i] >= 1) {
                    print.append(products[i] + " " + quantity[i] + " шт " + prices[i] + " руб/шт " + quantityProduct[i] + " руб в сумме ");
                    print.append("\n");
                    setCount(quantity);
                    setCount2(quantityProduct);
                }
            }
            print.append("Итого: " + Arrays.stream(quantityProduct).sum() + " руб");

        System.out.println(print.toString());
        }

    public void saveBin(File binFile ) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(binFile ))) {
            objectOutputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Basket loadBinFile(File binFile) throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(binFile))) {
            Basket basket = (Basket) objectInputStream.readObject();
            System.out.print("В вашей корзине было" + "\n");
            basket.printCart();
            return basket;
        }

    }
}








