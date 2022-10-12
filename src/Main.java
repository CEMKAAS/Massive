import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};

        File binFile = new File("basket.bin");
        Basket basket = new Basket(products, prices);
        Scanner scanner = new Scanner(System.in);

        if (binFile.exists()) {
            basket.loadBinFile(binFile);
        } else {
            System.out.println("Список возможных товаров для покупки");
            for (int i = 0; i < products.length; i++) {
                System.out.println((i + 1) + "." + " " + products[i] + " " + prices[i] + " руб/шт");
            }
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

                basket.addToCart(product, pricesOne);
                basket.saveBin(binFile);

            } catch (NumberFormatException e) {
                System.out.println("Нужно ввести два числа, а вы ввели " + input);
                continue;
            }
        }
    }
}


