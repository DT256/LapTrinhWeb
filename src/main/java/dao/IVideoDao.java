package dao;

import entity.Video;

import java.io.Serializable;
import java.util.List;

public interface IVideoDao extends Serializable {
    List<Video> findAll();
    List<Video> findAll(int page, int pagesize);
    Video findById(int id);
    Video findByName(String name);
    List<Video> searchByName(String keyword);
    void insert(Video video);
    void update(Video video);
    void delete(int id) throws Exception;
    int count();
}
