package ru.ifmo.se.labs.asurkis.lab3;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
        return pointStyle(radiusCount, matchCount);
    }

    private String pointStyle(int radiusCount, int matchCount) {
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
