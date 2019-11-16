package ru.ifmo.se.labs.asurkis.lab3;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "queries")
public class Query implements Serializable {
    private int id;
    private Point point;
    private double radius;

    public Query() {
        this(new Point(), 0);
    }

    public Query(Point point, double radius) {
        this.point = point;
        this.radius = radius;
    }

    @Column(name = "result")
    public boolean getResult() {
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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Column(name = "radius")
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public void setResult(boolean result) {}
}
