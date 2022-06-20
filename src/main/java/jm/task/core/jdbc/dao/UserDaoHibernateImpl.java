package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

import static jm.task.core.jdbc.util.Util.HibernateUtil.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.createSQLQuery("CREATE TABLE IF NOT EXISTS userstable (\n" +
                "  `id` BIGINT(18) NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(20) NOT NULL,\n" +
                "  `lastName` VARCHAR(20) NULL,\n" +
                "  `age` TINYINT(3) NOT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB\n" +
                "DEFAULT CHARACTER SET = utf8").addEntity(User.class).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("DROP TABLE IF EXISTS userstable").addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User(name, lastName, age);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        User user = new User();
        user.setId(id);
        session.delete("id", user);

        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createNativeQuery("SELECT * FROM userstable;").addEntity(User.class);
        List<User> userList = query.list();
        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery("TRUNCATE userstable").executeUpdate();
        transaction.commit();
        session.close();
    }
}
