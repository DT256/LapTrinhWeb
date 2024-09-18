package dao;

import models.UserModel;

import java.util.List;

public interface IUserDao {
    UserModel findByUsername(String username);
    UserModel findById(int id);
    List<UserModel> findAll();
    void insertUser(UserModel user);
    boolean updatePassword(String username, String password);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    UserModel findByUsernameAndEmail(String username, String email);
}
