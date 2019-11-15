package ru.ifmo.se.labs.asurkis.lab3;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "points")
public class Point implements Serializable {
    private int id;
    private int queryCount;
    private double x;
    private double y;

    public Point() {
        this(0, 0);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public boolean fallsInto(double r) {
        if (x > 0) {
            if (y > 0) {
                return x * x + y * y <= r * r;
            } else if (y < 0) {
                return x - y <= 0.5 * r;
            } else {
                return x <= r;
            }
        } else if (x < 0) {
            if (y > 0) {
                return -r <= x && y <= 0.5 * r;
            } else if (y < 0) {
                return false;
            } else {
                return -r <= x;
            }
        } else {
            return -0.5 * r <= y && y <= r;
        }
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Id
    @Column(name = "point_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "point_query_count")
    public int getQueryCount() {
        return queryCount;
    }

    public void setQueryCount(int queryCount) {
        this.queryCount = queryCount;
    }

    @Column(name = "point_x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column(name = "point_y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
