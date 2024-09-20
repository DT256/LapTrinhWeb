package services.impl;

import dao.IUserDao;
import dao.impl.UserDaoImpl;
import models.UserModel;
import services.IUserService;

import java.util.Date;

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

    @Override
    public UserModel findByUserNameAndEmail(String username, String email) {
        return userDao.findByUsernameAndEmail(username, email);
    }

    @Override
    public boolean updatePassword(String username, String newPassword) {
        return userDao.updatePassword(username, newPassword);
    }

    @Override
    public void insertUser(UserModel user) {
        userDao.insertUser(user);
    }

    @Override
    public boolean register(String username, String email, String fullname, String password, int roleid) {
        if(userDao.checkExistUsername(username)) {
            return false;
        }
        if(userDao.checkExistEmail(email)) {
            return false;
        }
        UserModel user = new UserModel();
        user.setUsername(username);
        user.setFullname(fullname);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoleid(roleid);

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        user.setCreateDate(date);

        this.insertUser(user);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    public static void main(String[] args) {
        try {
            //long millis = System.currentTimeMillis();
            //Date sqlDate = new Date(millis);
            //System.out.print(sqlDate);
            UserServiceImpl userService = new UserServiceImpl();
            //System.out.println(userService.register("1123","ddd@gmail.com","1234",2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
