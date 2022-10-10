import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Basket {
    String[] products;
    int[] prices;
    int[] quantity = new int[10];
    int[] quantityProduct = new int[10];
    public Basket (String[] products, int[] prices){
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

//        public void printCart(){
//            System.out.println("Список возможных товаров для покупки");
//            for (int i = 0; i < products.length; i++) {
//                System.out.println((i + 1) + "." + " " + products[i] + " " + prices[i] + " руб/шт");
//            }
//        }

       public void saveTxt(File textFile) throws IOException {
            try (PrintWriter out = new PrintWriter(file);) {{
                   }


        static Basket loadFromTxtFile(File textFile){


        }



    }

}
