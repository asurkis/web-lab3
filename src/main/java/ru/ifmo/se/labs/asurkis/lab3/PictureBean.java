package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name="picture")
@ViewScoped
public class PictureBean implements Serializable {
    public String pointStyle(Point point, InputBean inputBean) {
        int radiusCount = 0;
        int matchCount = 0;
        for (int r = 1; r <= 5; r++) {
            if (inputBean.getRs()[r - 1]) {
                radiusCount++;
                if (point.fallsInto(r)) {
                    matchCount++;
                }
            }
        }
        return pointStyleByCount(radiusCount, matchCount);
    }

    public int getRadiusCount() {
        FacesContext context = FacesContext.getCurrentInstance();
        InputBean inputBean = context.getApplication().evaluateExpressionGet(context, "#{input}", InputBean.class);

        int result = 0;
        for (int i = 0; i < 5; i++) {
            if (inputBean.getRs()[i])
                result++;
        }
        return result;
    }

    public String pointStyleByCount(int radiusCount, int matchCount) {
        if (radiusCount == 0) {
            return "stroke: #000; fill: #888";
        }

        double a = (double) matchCount / radiusCount;
        double r = 255 * Math.sqrt(1 - a);
        double g = 255 * Math.sqrt(a);
        double b = 0;
        int ri = (int) r;
        int gi = (int) g;
        int bi = (int) b;

        return String.format("stroke: #000; fill: rgb(%d, %d, %d)", ri, gi, bi);
    }
}
