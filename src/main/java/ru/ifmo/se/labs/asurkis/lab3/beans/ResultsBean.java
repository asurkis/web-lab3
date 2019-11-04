package ru.ifmo.se.labs.asurkis.lab3.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@SessionScoped
@ManagedBean(name="results")
public class ResultsBean {
    private List<Result> results = new ArrayList<>();

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
        ResultsBean that = (ResultsBean) o;
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
