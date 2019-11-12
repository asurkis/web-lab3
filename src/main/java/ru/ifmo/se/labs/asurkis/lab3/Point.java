package ru.ifmo.se.labs.asurkis.lab3;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Point implements Serializable {
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

    @Column(name = "query_point_x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @Column(name = "query_point_y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
