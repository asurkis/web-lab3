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

import static ru.ifmo.se.labs.asurkis.lab3.Utils.getSessionId;

@ManagedBean(name="model")
@SessionScoped
public class ModelBean implements Serializable {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("QueriesDB");

    public void addPoint(Point p) {
        p.setSessionId(getSessionId());

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
        manager.close();
    }

    public void addQuery(Query q) {
        Point p = q.getPoint();

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        p.setQueryCount(p.getQueryCount() + 1);
        manager.merge(p);
        manager.persist(q);
        manager.getTransaction().commit();
        manager.close();
    }

    public void removeQuery(int queryId) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        Query q = manager.find(Query.class, queryId);
        Point p = q.getPoint();

        if (getSessionId().equals(p.getSessionId())) {
            manager.remove(q);
            p.setQueryCount(p.getQueryCount() - 1);
        }

        if (p.getQueryCount() > 0) {
            manager.merge(p);
        } else {
            manager.remove(p);
        }

        manager.getTransaction().commit();
        manager.close();
    }

    public List<Point> getPoints() {
        EntityManager manager = factory.createEntityManager();
        javax.persistence.Query query = manager.createQuery("select p from Point p where p.sessionId=:sessionId");
        query.setParameter("sessionId", getSessionId());
        List<Point> result = query.getResultList();
        manager.close();
        return result;
    }

    public List<Query> getQueries() {
        EntityManager manager = factory.createEntityManager();
        javax.persistence.Query query = manager.createQuery("select q from Query q where (select p.sessionId from Point p where p=q.point)=:sessionId");
        query.setParameter("sessionId", getSessionId());
        List<Query> result = query.getResultList();
        manager.close();
        return result;
    }
}
