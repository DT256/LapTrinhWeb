package services;

import entity.Category;
import models.CategoryModel;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(int id);
    Category findByName(String name);
    List<Category> searchByName(String keyword);
    void insert(Category category);
    void update(Category category);
    void delete(int id) throws Exception;
}
