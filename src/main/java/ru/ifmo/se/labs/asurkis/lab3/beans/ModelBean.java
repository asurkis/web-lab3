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
    private List<Result> results = new CopyOnWriteArrayList<>();

    public void addPoint(double x, double y, double[] rs) {
        results.add(new Result(new Point(x, y), rs));
    }

    @Override
    public String toString() {
        return "ResultsBean{" +
                "results=" + results +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelBean that = (ModelBean) o;
        return Objects.equals(results, that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results);
    }

    public List<Result> getResults() {
        return results;
    }
}
