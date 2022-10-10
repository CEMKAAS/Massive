import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File textFile = new File("src/textFile.txt");
        PrintWriter out = new PrintWriter(textFile);
        out.println();
        out.close();

        InputStream in = new FileInputStream(textFile);
        Scanner scanner1 = new Scanner(in);
        String name = scanner1.nextLine();
        String[] asd = scanner1.nextLine().split("");

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Яблоки", "Молоко"};
        int[] prices = {100, 200, 300};
        Basket basket = new Basket(products,prices);

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + "." + " " + products[i] + " " + prices[i] + " руб/шт");
        }

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

        }
    }
}

