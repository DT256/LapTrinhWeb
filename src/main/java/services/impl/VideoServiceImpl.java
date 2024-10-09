package services.impl;

import dao.IVideoDao;
import dao.impl.VideoDaoImpl;
import entity.Video;
import services.IVideoService;
import java.util.List;

public class VideoServiceImpl implements IVideoService {
    public IVideoDao videoDao = new VideoDaoImpl();
    @Override
    public List<Video> findAll() {
        return videoDao.findAll();
    }

    @Override
    public Video findById(int id) {
        return videoDao.findById(id);
    }

    @Override
    public Video findByName(String name) {
        return videoDao.findByName(name);
    }

    @Override
    public List<Video> searchByName(String keyword) {
        return videoDao.searchByName(keyword);
    }

    @Override
    public void insert(Video video) {
        // goi ham ktra trung name
        Video vid = this.findByName(video.getTitle());
        if (vid == null) {
            videoDao.insert(video);
        }
    }

    @Override
    public void update(Video video) {
        //videoDao.update(video);
        Video vid = this.findByName(video.getTitle());
        System.out.println(vid == null);
        if (vid == null) {
            videoDao.update(video);
        }
    }

    @Override
    public void delete(int id) throws Exception {
        videoDao.delete(id);
    }
}
