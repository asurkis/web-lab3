package ru.ifmo.se.labs.asurkis.lab3.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

public class Result implements Serializable {
    private Point point;
    private double[] radiuses;

    public Result(Point point, double[] radiuses) {
        this.point = point;
        this.radiuses = radiuses;
    }

    @Override
    public String toString() {
        return "Result{" +
                "point=" + point +
                ", radiuses=" + Arrays.toString(radiuses) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return point.equals(result.point) &&
                Arrays.equals(radiuses, result.radiuses);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(point);
        result = 31 * result + Arrays.hashCode(radiuses);
        return result;
    }

    public Point getPoint() {
        return point;
    }

    public double[] getRadiuses() {
        return radiuses;
    }
}
