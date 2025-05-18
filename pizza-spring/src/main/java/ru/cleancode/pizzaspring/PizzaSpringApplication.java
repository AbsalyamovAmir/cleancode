package ru.cleancode.pizzaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaSpringApplication.class, args);
    }

}

//на всякий случай из старого модуля
//public static void main(String[] args) {
//        try {
//            DodoPizza dodoPizza = DodoPizza.getInstance();
//
//            List<Dish> dishes = new ArrayList<>();
//            Margherita margherita = new Margherita(PizzaSize.SMALL, BigDecimal.valueOf(250));
//            margherita.setIngredients();
//            ChicagoDeepDish chicagoDeepDish = new ChicagoDeepDish(PizzaSize.MEDIUM, BigDecimal.valueOf(300));
//            chicagoDeepDish.setIngredients();
//            Okonomiyaki okonomiyaki = new Okonomiyaki(PizzaSize.LARGE, BigDecimal.valueOf(350));
//            okonomiyaki.setIngredients();
//
//            dishes.add(margherita);
//            dishes.add(chicagoDeepDish);
//            dishes.add(okonomiyaki);
//
//            Order order = Order.builder().dishes(dishes).build();
//            List<Order> orders = new ArrayList<>();
//            orders.add(order);
//
//            Client client = new Client("Вася", orders);
//            log.info(client.toString());
//            dodoPizza.doJob(orders);
//
//            List<Dish> dishesNew = new ArrayList<>();
//            Pizza margheritaNew = new Margherita(PizzaSize.LARGE, BigDecimal.valueOf(350));
//            margheritaNew = new ExtraCheeseDecorator(margheritaNew);
//            Pizza chicagoDeepDishNew = new ChicagoDeepDish(PizzaSize.LARGE, BigDecimal.valueOf(450));
//            chicagoDeepDishNew = new PepperoniDecorator(chicagoDeepDishNew);
//            dishesNew.add(chicagoDeepDishNew);
//            dishesNew.add(margheritaNew);
//            dishesNew.add(okonomiyaki);
//            Order orderNew = Order.builder().dishes(dishesNew).build();
//            orders.add(orderNew);
//            dodoPizza.doJob(orders);
//
//            Client clientNew = new Client("Петя", orders);
//            log.info(clientNew.toString());
//
//            orders.add(orderNew);
//            dodoPizza.doJob(orders);
//        } catch (OrderLimitExceededException | KitchenTypeException e) {
//            log.error(e.getMessage());
//        }
//    }
