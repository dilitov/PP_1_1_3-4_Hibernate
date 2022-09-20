package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createNativeQuery("CREATE TABLE IF NOT EXISTS User "
                    + "(Id INT UNSIGNED NOT NULL AUTO_INCREMENT, "
                    + "name VARCHAR(20)  NOT NULL,"
                    + "lastName VARCHAR(20)  NOT NULL, "
                    + "age TINYINT NOT NULL, PRIMARY KEY (id));").executeUpdate();
            transaction.commit();
        }catch(HibernateException e){
                e.printStackTrace();
            }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession())
        {
        Transaction transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS User").executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            e.printStackTrace();
        }
        }



    @Override
    public void saveUser(String name, String lastName, byte age) {
       try (Session session = Util.getSessionFactory().openSession())
       {
        Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession())
        {
        Transaction transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession())
        {
        Transaction transaction = session.getTransaction();

            userList = session.createQuery("FROM User").list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession())
        {
        Transaction transaction = session.beginTransaction();
            session.createNativeQuery("TRUNCATE TABLE User").executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}


