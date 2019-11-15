package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name="model")
@SessionScoped
public class ModelBean implements Serializable {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("QueriesDB");

    public void addPoint(Point p) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
        manager.close();
    }

    public void addQuery(Query q) {
        q.setSessionId(getSessionId());
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(q);
        manager.getTransaction().commit();
        manager.close();
    }

    public void removeQuery(int queryId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query query = manager.find(Query.class, queryId);
        if (getSessionId().equals(query.getSessionId())) {
            manager.remove(query);
        }
        manager.getTransaction().commit();
        manager.close();
    }

    public List<Point> getPoints() {
        EntityManager manager = factory.createEntityManager();
        javax.persistence.Query query = manager.createQuery("select p from Point p");
        List<Point> result = query.getResultList();
        manager.close();
        return result;
    }

    public Point getPointById(int id) {
        EntityManager manager = factory.createEntityManager();
        javax.persistence.Query query = manager.createQuery("select p from Point p where p.id=:pointId");
        query.setParameter("pointId", id);
        Point result = (Point) query.getSingleResult();
        manager.close();
        return result;
    }

    public List<Query> getQueries() {
        EntityManager manager = factory.createEntityManager();
        javax.persistence.Query query = manager.createQuery("select q from Query q where q.sessionId=:sessionId");
        query.setParameter("sessionId", getSessionId());
        List<Query> result = query.getResultList();
        manager.close();
        return result;
    }

    private String getSessionId() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        return session.getId();
    }
}
