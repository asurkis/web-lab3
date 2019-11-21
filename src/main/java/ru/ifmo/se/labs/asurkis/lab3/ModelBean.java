package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "model")
@SessionScoped
public class ModelBean implements Serializable {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("QueriesDB");
    private User currentUser;

    public void addQueries(Query... queries) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        for (Query q : queries) {
            Point p = q.getPoint();
            p.setUser(currentUser);
            manager.persist(p);
            manager.persist(q);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public void updateQueries(List<Query> queryList) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        for (Query query: queryList) {
            Query q = manager.find(Query.class, query.getId());
            Point p = q.getPoint();
            if (currentUser.getId() != p.getUser().getId()) {
                continue;
            }
            if (query.isToDelete()) {
                manager.remove(q);
            } else {
                q.setRadius(query.getRadius());
                manager.merge(q);
            }
        }
        manager.createQuery("DELETE FROM Point p WHERE 0=(SELECT COUNT(*) FROM Query q WHERE p=q.point)").executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    public void addObjects(Object... objects) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        for (Object o : objects) {
            manager.persist(o);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public void removeQuery(int queryId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Query q WHERE :queryId=q.id AND :user=(SELECT p.user FROM Point p WHERE p=q.point)")
                .setParameter("queryId", queryId)
                .setParameter("user", currentUser)
                .executeUpdate();
        manager.createQuery("DELETE FROM Point p WHERE 0=(SELECT COUNT(*) FROM Query q WHERE p=q.point)").executeUpdate();

        manager.getTransaction().commit();
        manager.close();
    }

    public void removeUser(int userId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Query q WHERE :userId=(SELECT p.user.id FROM Point p WHERE p=q.point)").setParameter("userId", userId).executeUpdate();
        manager.createQuery("DELETE FROM Point p WHERE :userId=(SELECT u.id FROM User u WHERE u=p.user)").setParameter("userId", userId).executeUpdate();
        manager.createQuery("DELETE FROM User  u WHERE :userId=u.id").setParameter("userId", userId).executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    public void removeUser(User user) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Query q WHERE :user=(SELECT p.user FROM Point p WHERE p=q.point)").setParameter("user", user).executeUpdate();
        manager.createQuery("DELETE FROM Point p WHERE :user=p.user").setParameter("user", user).executeUpdate();
        manager.remove(user);
        manager.getTransaction().commit();
        manager.close();
    }

    public void removeUnusedPoints() {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.createQuery("DELETE FROM Point p WHERE 0=(SELECT COUNT(*) FROM Query q WHERE p=q.point)").executeUpdate();
        manager.getTransaction().commit();
        manager.close();
    }

    public User findUser(int userId) {
        EntityManager manager = factory.createEntityManager();
        User result = manager.find(User.class, userId);
        manager.close();
        return result;
    }

    public boolean userExists(String username) {
        EntityManager manager = factory.createEntityManager();
        Boolean result = manager.createQuery("SELECT COUNT(*)<>0 FROM User u where :username=u.name", Boolean.class).setParameter("username", username).getSingleResult();
        manager.close();
        return result;
    }

    public List<Point> getPoints() {
        EntityManager manager = factory.createEntityManager();
        List<Point> result = manager.createQuery(
                "SELECT p FROM Point p WHERE :user=p.user ORDER BY p.id", Point.class
        ).setParameter("user", currentUser).getResultList();
        manager.close();
        return result;
    }

    public List<Query> getQueries() {
        EntityManager manager = factory.createEntityManager();
        List<Query> result = manager.createQuery(
                "SELECT q FROM Query q WHERE :user=q.point.user ORDER BY q.id", Query.class
        ).setParameter("user", currentUser).getResultList();
        manager.close();
        return result;
    }

    public List<User> getUsers() {
        EntityManager manager = factory.createEntityManager();
        List<User> result = manager.createQuery("SELECT u FROM User u ORDER BY u.id", User.class).getResultList();
        manager.close();
        return result;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(int userId) {
        setCurrentUser(findUser(userId));
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
