package com.sbt;

import com.sbt.model.Bid;
import com.sbt.model.Item;
import com.sbt.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.List;
import java.util.Properties;

public class Main {
    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry = null;

    private static SessionFactory configureSessionFactory() throws HibernateException {
        Configuration configuration = new Configuration();
        configuration.configure();
        Properties properties = configuration.getProperties();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(properties).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }

    public static void main(String[] args) {
        try {
            configureSessionFactory();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            User user = new User();
            user.setName("name1");
            Item item = new Item();
            item.setName("ItemName");
            item.setDescription("ItemDescription");
            item.setUser(user);
            Bid bid = new Bid();
            bid.setUser(user);
            bid.setItem(item);

            session.persist(user); // сохранение пользователей в БД
            session.persist(item); // сохранение товаров в БД
            session.persist(bid); // сохранение ставок в БД
            tx.rollback();

            tx = session.beginTransaction();
            List<User> list = session.createCriteria(User.class).list();
            List<Item> listItem = session.createCriteria(Item.class).list();
            for (User userItem : list) {
                System.out.println(userItem.getName());
            }
            session.flush();
            tx.commit();
        } catch (Exception ex) {
            ex.printStackTrace();
            tx.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}