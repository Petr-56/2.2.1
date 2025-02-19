package hiber;

import hiber.config.AppConfig;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

// Создайте несколько пользователей с машинами, добавьте их в
   //базу данных, вытащите обратно.
public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      CarService carService = context.getBean(CarService.class);
      List<Car> cars = carService.listCars();

      carService.add(new Car("Renault", "123"));
      carService.add(new Car("Saab", "234"));
      carService.add(new Car("Skoda", "345"));
      carService.add(new Car("Ssangyong", "456"));

      UserService userService = context.getBean(UserService.class);
      List<Car> cars = carService.listCars();

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car.get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car.get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car.get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car.get(3)));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      context.close();
   }
}
