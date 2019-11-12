package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.parser.Entity;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@ManagedBean(name="model")
@SessionScoped
public class ModelBean implements Serializable {
    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("QueriesDB");

    public void addQuery(double x, double y, double r) {
        Query query = new Query();
        query.setPoint(new Point(x, y));
        query.setRadius(r);
        query.setSessionId(getSessionId());

        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(query);
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
