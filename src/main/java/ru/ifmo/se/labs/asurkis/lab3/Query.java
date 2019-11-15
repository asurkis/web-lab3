package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "queries")
public class Query implements Serializable {
    private int id;
    private String sessionId;
    private Point point;
    private double radius;

    public Query() {
        this(new Point(), 0);
    }

    public Query(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    public boolean evaluateResult() {
        return point.fallsInto(radius);
    }

    @Override
    public String toString() {
        return "Result{" +
                "point=" + point +
                ", radius=" + radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Query query = (Query) o;
        return Double.compare(query.radius, radius) == 0 &&
                Objects.equals(point, query.point);
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, radius);
    }

    @Id
    @Column(name = "query_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "query_session_id")
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Transient
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Column(name = "query_point_id")
    public int getPointId() {
        return point.getId();
    }

    public void setPointId(int pointId) {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        this.point = modelBean.getPointById(pointId);
    }

    @Column(name = "query_radius")
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
