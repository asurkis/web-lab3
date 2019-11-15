package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@ManagedBean(name="input")
@ViewScoped
public class InputBean implements Serializable {
    private double x;
    private double y;
    private boolean[] rs = new boolean[5];

    public void addCurrent() {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);

        Point point = new Point(x, y);
        modelBean.addPoint(point);

        for (int i = 0; i < rs.length; i++) {
            if (rs[i]) {
                modelBean.addQuery(new Query(point, i + 1));
            }
        }
    }

    @Override
    public String toString() {
        return "InputBean{" +
                "x=" + x +
                ", y=" + y +
                ", rs=" + Arrays.toString(rs) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputBean inputBean = (InputBean) o;
        return x == inputBean.x &&
                Double.compare(inputBean.y, y) == 0 &&
                Arrays.equals(rs, inputBean.rs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(x, y);
        result = 31 * result + Arrays.hashCode(rs);
        return result;
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
