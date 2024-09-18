package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import configs.DBConnectMySQL;
import configs.DBConnectSQLServer;
import dao.IUserDao;
import models.UserModel;

public class UserDaoImpl implements IUserDao{

    @Override
    public UserModel findByUsername(String username) {
        String sql = "select * from Users where username = ?";
        try {
            Connection conn = new DBConnectMySQL().getDatabaseConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            UserModel user = new UserModel();
            while(rs.next()) {
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setFullname(rs.getString("image"));
                user.setCreateDate(rs.getDate("createDate"));
                user.setRoleid(rs.getInt("roleid"));
                user.setFullname(rs.getString("phone"));
            }
            return user;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        try {
            IUserDao userDao = new UserDaoImpl();

            System.out.println(userDao.findByUsername("thangbd"));

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


}

