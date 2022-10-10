import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Basket {
    String[] products;
    int[] prices;

    public Basket (String[] products, int[] prices){
        this.prices = prices;
        this.products = products;
    }

   public void addToCart(int productNum, int amount){
       try {
           String[] parts = input.split(" ");
           if (parts.length != 2) {
               System.out.println("Нужно ввести два числа на одной строке");
               continue;
           }

           int product = Integer.parseInt(parts[0]);

           if ((0 >= product) || (product >= products.length)) {
               System.out.println("В параметре надо указать коректный товар " + product);
               continue;
           }

           product--;

           int pricesOne = Integer.parseInt(parts[1]);
           if (pricesOne <= 0) {
               System.out.println("Введите положительное число " + pricesOne);
               continue;
           }

           if (product < (products.length + 1) && product >= 0) {
               quantity[product] += pricesOne;
               int currentPrice = prices[product];
               quantityProduct[product] = quantity[product] * currentPrice;
           } else {
               System.out.println("Такого товара нет");
           }
       } catch (NumberFormatException e) {
           System.out.println("Нужно ввести два числа, а вы ввели " + input);
           continue;
       }
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
