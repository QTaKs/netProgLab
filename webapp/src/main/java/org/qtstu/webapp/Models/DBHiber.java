package org.qtstu.webapp.Models;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DBHiber extends DBI{
    SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();

    @Override
    public List<UserRecord> getUserRecords() {
        List<UserRecord> export;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<UserRecord> criteria = builder.createQuery(UserRecord.class);
            criteria.from(UserRecord.class);
            export = session.createQuery(criteria).getResultList();
        }
        return export;
    }

    @Override
    public List<UserRecord> getUserRecord(Long id) {
        List<UserRecord> export = new ArrayList<UserRecord>();
        try (Session session = sessionFactory.openSession()) {
            UserRecord user = session.get(UserRecord.class, id);
            export.add(user);
        }
        return export;
    }

    @Override
    public void addUsers(List<UserRecord> users) {
        users.forEach(userRecord -> {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(userRecord);
                transaction.commit();
            }
        });
    }

    @Override
    public void updateUsers(List<UserRecord> users) {
        users.forEach(userRecord -> {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.merge(userRecord);
                transaction.commit();
            }
        });
    }

    @Override
    public void deleteUser(Long id) {
        try (Session session = sessionFactory.openSession()) {
            UserRecord  user = getUserRecord(id).get(0);
            Transaction transaction = session.beginTransaction();
            session.remove(user);
            transaction.commit();
        }
    }

    @Override
    public List<VideoRecord> getVideoRecords(Long userId) {
        List<VideoRecord> export;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<VideoRecord> criteria = builder.createQuery(VideoRecord.class);
            Root<VideoRecord> from = criteria.from(VideoRecord.class);
            criteria.where(session.getCriteriaBuilder().equal(from.get("uploader"),userId));
            criteria.from(VideoRecord.class);
            export = session.createQuery(criteria).getResultList();
        }
        return export;
    }

    @Override
    public List<VideoRecord> getVideoRecordsAll() {
        List<VideoRecord> export;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<VideoRecord> criteria = builder.createQuery(VideoRecord.class);
            criteria.from(VideoRecord.class);
            export = session.createQuery(criteria).getResultList();
        }
        return export;
    }

    @Override
    public List<VideoRecord> getVideoRecord(Long userId, Long videoId) {
        List<VideoRecord> export;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<VideoRecord> criteria = builder.createQuery(VideoRecord.class);
            Root<VideoRecord> from = criteria.from(VideoRecord.class);
            criteria.where(session.getCriteriaBuilder().equal(from.get("id"),videoId));
            criteria.where(session.getCriteriaBuilder().equal(from.get("uploader"),userId));
            criteria.from(VideoRecord.class);
            export = session.createQuery(criteria).getResultList();
        }
        return export;
    }

    @Override
    public void addVideos(List<VideoRecord> videos, Long userId) {
        videos.forEach(videoRecord -> {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.persist(videoRecord);
                transaction.commit();
            }
        });
    }

    @Override
    public void updateVideos(List<VideoRecord> videos, Long userId) {
        videos.forEach(videosRecord -> {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.merge(videosRecord);
                transaction.commit();
            }
        });
    }

    @Override
    public void deleteVideo(Long userId, Long videoId) {
        try (Session session = sessionFactory.openSession()) {
            VideoRecord  video = getVideoRecord(userId,videoId).get(0);
            Transaction transaction = session.beginTransaction();
            session.remove(video);
            transaction.commit();
        }
    }

    @Override
    public void deleteVideoTrash() {
        List<VideoRecord> export;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();

            CriteriaQuery<VideoRecord> criteria = builder.createQuery(VideoRecord.class);
            Root<VideoRecord> from = criteria.from(VideoRecord.class);
            criteria.where(session.getCriteriaBuilder().lessThan(from.get("duration"),30*60));
            criteria.from(VideoRecord.class);
            export = session.createQuery(criteria).getResultList();
        }
        export.forEach(videoRecord -> {
            try (Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                session.remove(videoRecord);
                transaction.commit();
            }
        });
    }
}
