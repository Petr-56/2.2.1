package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.Currency;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User findByCar(String car_model, String car_series) {
      TypedQuery<Car> findCarQuery = sessionFactory.getCurrentSession().createQuery("from Car where model = :car_model and series = :car_series")
              .setParameter("car_model", car_model)
              .setParameter("car_series", car_series);
      List<Car> findCarList = findCarQuery.getResultList();
      if (!findCarList.isEmpty()) {
         Car findCar = findCarList.get(0);
         List<User> ListUser = listUsers();
         User FindUser = ListUser.stream()
                 .filter(user -> user.getCar().equals(findCar))
                 .findAny()
                 .orElse(null);
         return FindUser;
      }
      return null;
   }
}
