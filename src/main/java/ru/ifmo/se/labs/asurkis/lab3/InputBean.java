package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@ManagedBean(name="input")
@ViewScoped
public class InputBean implements Serializable {
    private int userId;
    private String newUsername;
    private double x;
    private double y;
    private boolean[] rs = new boolean[5];
    private List<Query> queryList;

    {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        queryList = modelBean.getQueries();
    }

    public void addCurrent() {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);

        Point point = new Point(x, y);
        int queryCount = 0;
        for (int i = 0; i < rs.length; i++) {
            if (rs[i]) {
                queryCount++;
            }
        }

        int queryId = 0;
        Query[] queries = new Query[queryCount];

        for (int i = 0; i < rs.length; i++) {
            if (rs[i]) {
                queries[queryId++] = new Query(point, i + 1);
            }
        }

        modelBean.addQueries(queries);
        queryList = modelBean.getQueries();
    }

    public void addUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);

        modelBean.addObjects(new User(newUsername));
    }

    public void removeUser() {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        modelBean.removeUser(userId);
    }

    public void updateQueries() {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        modelBean.updateQueries(queryList);
        queryList = modelBean.getQueries();
    }

    @Override
    public String toString() {
        return "InputBean{" +
                "username='" + newUsername + '\'' +
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
                Objects.equals(newUsername, inputBean.newUsername) &&
                Arrays.equals(rs, inputBean.rs);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(newUsername, x, y);
        result = 31 * result + Arrays.hashCode(rs);
        return result;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        modelBean.setCurrentUser(userId);
        this.userId = userId;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
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

    public List<Query> getQueryList() {
        return queryList;
    }

    public void setQueryList(List<Query> queryList) {
        this.queryList = queryList;
    }
}
