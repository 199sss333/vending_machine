import enums.ActionLetter;
import model.*;
import model.MoneyAcceptor;
import util.UniversalArray;
import util.UniversalArrayImpl;

import java.util.Scanner;

public class AppRunner {

    private final UniversalArray<Product> products = new UniversalArrayImpl<>();
    private final MoneyAcceptor moneyAcceptor;

    private static boolean isExit = false;

    private AppRunner(MoneyAcceptor moneyAcceptor) {
        this.moneyAcceptor = moneyAcceptor;

        products.addAll(new Product[]{
                new Water(ActionLetter.B, 20),
                new CocaCola(ActionLetter.C, 50),
                new Soda(ActionLetter.D, 30),
                new Snickers(ActionLetter.E, 80),
                new Mars(ActionLetter.F, 80),
                new Pistachios(ActionLetter.G, 130)
        });
    }

    public static void run() {
        MoneyAcceptor acceptor = new CashAcceptor(100);
        AppRunner app = new AppRunner(acceptor);

        while (!isExit) {
            app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);

        print("Баланс: " + moneyAcceptor.getCurrentBalance() + " руб.");

        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (moneyAcceptor.getCurrentBalance() >= products.get(i).getPrice()) {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        showActions(products);
        print(" h - Выйти");

        String action = fromConsole().substring(0, 1);
        try {
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);

                if (product.getActionLetter().equals(ActionLetter.valueOf(action.toUpperCase()))) {
                    moneyAcceptor.removeMoney(product.getPrice());
                    print("Вы купили " + product.getName());
                    return;
                }
            }

            if ("h".equalsIgnoreCase(action)) {
                isExit = true;
            } else {
                print("Недопустимая буква. Попробуйте ещё раз.");
                chooseAction(products);
            }
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попробуйте ещё раз.");
            chooseAction(products);
        }
    }

    private void showActions(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        return new Scanner(System.in).nextLine();
    }

    private void showProducts(UniversalArray<Product> products) {
        for (int i = 0; i < products.size(); i++) {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) {
        System.out.println(msg);
    }
}