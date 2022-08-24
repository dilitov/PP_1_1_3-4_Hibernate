package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    private static final UserService userService = new UserServiceImpl();
    private static final User user1 = new User("Mitroy", "Gopit", (byte) 60);
    private static final User user2 = new User("Jija", "Top", (byte) 15);
    private static final User user3 = new User("Stariy", "Pes", (byte) 126);
    private static final User user4 = new User("Legkiy", "Qyqer", (byte) 76);


    public static void main(String[] args) {
//        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();
//        userDao.createUsersTable();
//        userDao.saveUser("Name1", "LastName1", (byte) 20);
//        userDao.saveUser("Name2", "LastName2", (byte) 25);
//        userDao.saveUser("Name3", "LastName3", (byte) 31);
//        userDao.saveUser("Name4", "LastName4", (byte) 38);
        userDao.removeUserById(2);
//        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
