import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        File textFile = new File("src/textFile.txt");
        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};

        Basket basket = new Basket(products, prices);

        if (textFile.exists()){
            Basket.loadFromTxtFile(textFile);
            System.out.println(basket.printCart());
        } else {System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + "." + " " + products[i] + " " + prices[i] + " руб/шт");
        }}

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
               System.out.println(basket.printCart());
                break;
            }

            try {
                String[] parts = input.split(" ");
                if (parts.length != 2) {
                    System.out.println("Нужно ввести два числа на одной строке");
                    continue;
                }

                int product = Integer.parseInt(parts[0]);

                if ((0 >= product) || (product >= products.length+1)) {
                    System.out.println("В параметре надо указать коректный товар " + product);
                    continue;
                }

                product--;

                int pricesOne = Integer.parseInt(parts[1]);
                if (pricesOne <= 0) {
                    System.out.println("Введите положительное число " + pricesOne);
                    continue;
                }

                basket.addToCart(product, pricesOne);
                basket.saveTxt(textFile);
            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести два числа, а вы ввели " + input);
                continue;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}


