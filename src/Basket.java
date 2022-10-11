import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Basket {
    String[] products;
    int[] prices;

    static int[] quantity= new int[5];
    static int[] quantityProduct = new int[5];


    public Basket (String[] products, int[] prices) throws FileNotFoundException {
        this.prices = prices;
        this.products = products;
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
    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile);) {

            for (int e : quantity)
                if (e != 0) {
                    out.print(e + " ");
                }
            out.print("\n");
            for (int a : quantityProduct)
                if (a != 0) {
                    out.print(a + " ");
                }
        }
    }


    static Basket loadFromTxtFile(File textFile) throws FileNotFoundException {
        InputStream in = new FileInputStream(textFile);
        Scanner scanner1 = new Scanner(in);
        String[] asd = scanner1.nextLine().split(" ");
        String[] asd1 = scanner1.nextLine().split(" ");
        for (int i = 0; i < asd.length; i++) {
            quantity[i] = Integer.parseInt(asd[i]);
            quantityProduct[i] = Integer.parseInt(asd1[i]);
        }
        return null;
    }

    public String printCart () {
            StringBuilder print = new StringBuilder();
            print.append("Ваша корзина");
            print.append("\n");
            for (int i = 0; i < products.length; i++) {
                if (quantity[i] >= 1) {
                    print.append(products[i] + " " + quantity[i] + " шт " + prices[i] + " руб/шт " + quantityProduct[i] + " руб в сумме ");
                    print.append("\n");
                }
            }
            print.append("Итого: " + Arrays.stream(quantityProduct).sum() + " руб");

            return print.toString();
        }

    public saveBin(File file){}

    static loadFromBinFile(File file){
        
    }
    }








