package dao;

import models.UserModel;

public interface IUserDao {
    UserModel findByUsername(String username);
}
