package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.annotation.ManagedProperty;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@ManagedBean(name="input")
@ViewScoped
public class InputBean implements Serializable {
    @ManagedProperty(value = "#{model}")
    private ModelBean modelBean;

    private String username;
    private double x;
    private double y;
    private boolean[] rs = new boolean[5];

    public void addCurrent() {
        Point point = new Point(x, y);
        modelBean.addPoint(point);

        for (int i = 0; i < rs.length; i++) {
            if (rs[i]) {
                modelBean.addQuery(new Query(point, i + 1));
            }
        }
    }

    public void addUser() {
        modelBean.addUser(new User(username));
    }

    @Override
    public String toString() {
        return "InputBean{" +
                "username='" + username + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", rs=" + Arrays.toString(rs) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputBean inputBean = (InputBean) o;
        return Double.compare(inputBean.x, x) == 0 &&
                Double.compare(inputBean.y, y) == 0 &&
                Objects.equals(username, inputBean.username) &&
                Arrays.equals(rs, inputBean.rs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(username, x, y);
        result = 31 * result + Arrays.hashCode(rs);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean[] getRs() {
        return rs;
    }

    public void setRs(boolean[] rs) {
        this.rs = rs;
    }
}
