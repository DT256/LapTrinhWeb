package services;

import entity.Category;
import entity.Video;

import java.util.List;

public interface IVideoService {
    List<Video> findAll();
    Video findById(int id);
    Video findByName(String name);
    List<Video> searchByName(String keyword);
    void insert(Video video);
    void update(Video video);
    void delete(int id) throws Exception;
}
