import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Basket basket = new Basket(products, prices);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                System.out.println("Ваша корзина");
                for (int i = 0; i < products.length; i++) {
                    if (quantity[i] >= 1) {
                        System.out.println(products[i] + " " + quantity[i] + " шт " + prices[i] + " руб/шт " + quantityProduct[i] + " руб в сумме ");
                    }
                }
                System.out.println("Итого: " + Arrays.stream(quantityProduct).sum() + " руб");
                break;
            }

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

                basket.addToCart(product,pricesOne);

            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести два числа, а вы ввели " + input);
                continue;
            }
        }
    }
}


if (product < (products.length + 1) && product >= 0) {
        quantity[product] += pricesOne;
        int currentPrice = prices[product];
        quantityProduct[product] = quantity[product] * currentPrice;
        } else {
        System.out.println("Такого товара нет");








//    File textFile = new File("src/textFile.txt");
//    PrintWriter out = new PrintWriter(textFile);
//        out.println();
//        out.close();
//
//    InputStream in = new FileInputStream(textFile);
//    Scanner scanner1 = new Scanner(in);
//    String name = scanner1.nextLine();
//    String[] asd = scanner1.nextLine().split("");
//