package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
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
      TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(
                      "select u from User u join u.car c where c.model = :car_model and c.series = :car_series", User.class)
              .setParameter("car_model", car_model)
              .setParameter("car_series", car_series);
      List<User> users = query.getResultList();
      return users.isEmpty() ? null : users.get(0);
   }
}
