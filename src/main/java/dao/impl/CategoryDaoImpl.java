package dao.impl;

import configs.DBConnectMySQL;
import dao.ICategoryDao;
import models.CategoryModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    public Connection connection = null;
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    @Override
    public List<CategoryModel> findAll() {
        String sql = "select * from Categories";
        List<CategoryModel> list = new ArrayList<CategoryModel>();
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryid(resultSet.getInt("categoryid"));
                category.setCategoryname(resultSet.getString("categoryname"));
                category.setImage(resultSet.getString("image"));
                category.setStatus(resultSet.getInt("status"));
                list.add(category);
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CategoryModel findById(int id) {
        String sql = "select * from Categories where categoryid = ?";
        CategoryModel category = new CategoryModel();
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category.setCategoryid(resultSet.getInt("categoryid"));
                category.setCategoryname(resultSet.getString("categoryname"));
                category.setImage(resultSet.getString("image"));
                category.setStatus(resultSet.getInt("status"));
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
            return category;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public CategoryModel findByName(String name) {
        String sql = "select * from Categories where categoryname = ?";
        CategoryModel category = new CategoryModel();
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                category.setCategoryid(resultSet.getInt("categoryid"));
                category.setCategoryname(resultSet.getString("categoryname"));
                category.setImage(resultSet.getString("image"));
                category.setStatus(resultSet.getInt("status"));
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
            return category;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryModel> searchByName(String keyword) {
        String sql = "select * from Categories where categoryname like ?";
        List<CategoryModel> list = new ArrayList<CategoryModel>();
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CategoryModel category = new CategoryModel();
                category.setCategoryid(resultSet.getInt("categoryid"));
                category.setCategoryname(resultSet.getString("categoryname"));
                category.setImage(resultSet.getString("image"));
                category.setStatus(resultSet.getInt("status"));
                list.add(category);
            }
            connection.close();
            preparedStatement.close();
            resultSet.close();
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(CategoryModel category) {
        String sql = "insert into Categories(categoryname,image,status) values(?,?,?)";
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getCategoryname());
            preparedStatement.setString(2, category.getImage());
            preparedStatement.setInt(3, category.getStatus());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CategoryModel category) {
        String sql = "update Categories set categoryname=?,image=?,status=? where categoryid = ?";
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, category.getCategoryname());
            preparedStatement.setString(2, category.getImage());
            preparedStatement.setInt(3, category.getStatus());
            preparedStatement.setInt(4, category.getCategoryid());
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int id, int status) {
        String sql = "update Categories set status=? where categoryid = ?";
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "delete from Categories where categoryid = ?";
        try {
            connection = new DBConnectMySQL().getDatabaseConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            connection.close();
            preparedStatement.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
