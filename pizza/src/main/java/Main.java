import org.apache.logging.log4j.Logger;
import ru.cleancode.PizzaProject.exceptions.KitchenTypeException;
import ru.cleancode.PizzaProject.exceptions.OrderLimitExceededException;
import ru.cleancode.PizzaProject.objects.Client;
import ru.cleancode.PizzaProject.objects.Dish;
import ru.cleancode.PizzaProject.objects.Order;
import ru.cleancode.PizzaProject.objects.PizzaSize;
import ru.cleancode.PizzaProject.objects.kitchens.DodoPizza;
import ru.cleancode.PizzaProject.objects.pizzas.*;
import ru.cleancode.PizzaProject.utility.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            DodoPizza dodoPizza = DodoPizza.getInstance();

            List<Dish> dishes = new ArrayList<>();
            Margherita margherita = new Margherita(PizzaSize.SMALL, BigDecimal.valueOf(250));
            margherita.setIngredients();
            ChicagoDeepDish chicagoDeepDish = new ChicagoDeepDish(PizzaSize.MEDIUM, BigDecimal.valueOf(300));
            chicagoDeepDish.setIngredients();
            Okonomiyaki okonomiyaki = new Okonomiyaki(PizzaSize.LARGE, BigDecimal.valueOf(350));
            okonomiyaki.setIngredients();

            dishes.add(margherita);
            dishes.add(chicagoDeepDish);
            dishes.add(okonomiyaki);

            Order order = Order.builder().dishes(dishes).build();
            List<Order> orders = new ArrayList<>();
            orders.add(order);

            Client client = new Client("Вася", orders);
            log.info(client.toString());
            dodoPizza.doJob(orders);

            List<Dish> dishesNew = new ArrayList<>();
            Pizza margheritaNew = new Margherita(PizzaSize.LARGE, BigDecimal.valueOf(350));
            margheritaNew = new ExtraCheeseDecorator(margheritaNew);
            Pizza chicagoDeepDishNew = new ChicagoDeepDish(PizzaSize.LARGE, BigDecimal.valueOf(450));
            chicagoDeepDishNew = new PepperoniDecorator(chicagoDeepDishNew);
            dishesNew.add(chicagoDeepDishNew);
            dishesNew.add(margheritaNew);
            dishesNew.add(okonomiyaki);
            Order orderNew = Order.builder().dishes(dishesNew).build();
            orders.add(orderNew);
            dodoPizza.doJob(orders);

            Client clientNew = new Client("Петя", orders);
            log.info(clientNew.toString());

            orders.add(orderNew);
            dodoPizza.doJob(orders);
        } catch (OrderLimitExceededException | KitchenTypeException e) {
            log.error(e.getMessage());
        }
    }
}
