package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      userService.add(new User("Vladimir", "Putin", "kremlin_Moscow@russia.ru", new Car("lada kalina", 1337)));
      userService.add(new User("Boris", "Elcin", "BE@postUSSR.ru", new Car("VAZ 2107", 13321)));
      userService.add(new User("Iosif", "Stalin", "politBuroInRedSquare", new Car("T-34", 34)));
      userService.add(new User("Petr", "Romanov", "shipLover@port.ne", new Car("Fregat", 512)));
      userService.add(new User("Ivan", "Grozniy(Rurikovich)", "GrozniyVan@KazanIsRUS.ru", new Car("Loshad' Marusya", 1)));

      userService.listUsers().forEach(System.out::println);

      try {
         System.out.println(userService.getUserByCar("T-34", 34));
      } catch (org.hibernate.exception.DataException e) {
         System.out.println("Таких несколько!");
      }

      context.close();
   }
}