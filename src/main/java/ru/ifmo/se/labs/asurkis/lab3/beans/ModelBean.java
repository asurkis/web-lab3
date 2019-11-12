package ru.ifmo.se.labs.asurkis.lab3.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@ManagedBean(name="model")
@SessionScoped
public class ModelBean implements Serializable {
    private List<Query> queries = new CopyOnWriteArrayList<>();

    public void addQuery(double x, double y, double r) {
        queries.add(new Query(new Point(x, y), r));
    }

    public void removeQuery(Query query) {
        queries.remove(query);
    }

    @Override
    public String toString() {
        return "ResultsBean{" +
                "results=" + queries +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelBean that = (ModelBean) o;
        return Objects.equals(queries, that.queries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queries);
    }

    public List<Query> getQueries() {
        return queries;
    }
}
