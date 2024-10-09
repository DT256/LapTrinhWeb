package dao.impl;

import configs.JPAConfig;
import dao.IVideoDao;
import entity.Category;
import entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class VideoDaoImpl implements IVideoDao {
    @Override
    public List<Video> findAll() {
        EntityManager enma = JPAConfig.getEntityManager();
        TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
        return query.getResultList();
    }

    @Override
    public List<Video> findAll(int page, int pagesize) {
        return List.of();
    }

    @Override
    public Video findById(int id) {
        EntityManager enma = JPAConfig.getEntityManager();
        Video video = enma.find(Video.class, id);
        return video;
    }

    @Override
    public Video findByName(String name) {
        EntityManager enma = JPAConfig.getEntityManager();
        try {
            String jpql = "SELECT v FROM Video v WHERE v.title = :title";
            Video video = enma.createQuery(jpql, Video.class)
                    .setParameter("title", name)
                    .getSingleResult();
            return video;
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy Video với name: " + name);
            return null;
        } finally {
            enma.close();
        }
    }

    @Override
    public List<Video> searchByName(String keyword) {
        EntityManager enma = JPAConfig.getEntityManager();
        String jpql = "SELECT v FROM Video v WHERE v.title like :title";
        TypedQuery<Video> query = enma.createQuery(jpql, Video.class);
        query.setParameter("title", "%" + keyword + "%");
        enma.close();
        return query.getResultList();
    }

    @Override
    public void insert(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            //gọi phuong thức để insert, update, delete
            enma.persist(video);// ham insert
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void update(Video video) {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            //gọi phuong thức để insert, update, delete
            enma.merge(video);// ham insert
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            //gọi phuong thức để insert, update, delete
            Video video = enma.find(Video.class, id);
            if (video != null) {
                enma.remove(video);
            } else {
                throw new Exception("Không tìm thấy");
            }
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public int count() {
        return 0;
    }
}
