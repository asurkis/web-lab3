package ru.ifmo.se.labs.asurkis.lab3.beans;

import java.io.Serializable;
import java.util.Objects;

public class Query implements Serializable {
    private Point point;
    private double radius;

    public Query(Point point, double radius) {
        this.point = point;
        this.radius = radius;
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

    public boolean getResult() {
        return point.fallsInto(radius);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
