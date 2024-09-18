package services.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import models.UserModel;
import services.IUserService;

public class UserServiceImpl implements IUserService {
    IUserDao userDao = new UserDaoImpl();

    @Override
    public UserModel findByUserName(String username) {

        return userDao.findByUsername(username);
    }

    @Override
    public UserModel login(String username, String password) {
        UserModel user = this.findByUserName(username);
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            IUserService userService = new UserServiceImpl();

            System.out.println(userService.login("thangbd", "1234"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
