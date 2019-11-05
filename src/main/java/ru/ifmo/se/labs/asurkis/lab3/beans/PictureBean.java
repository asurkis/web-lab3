package ru.ifmo.se.labs.asurkis.lab3.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Arrays;

@ManagedBean(name="picture")
@ViewScoped
public class PictureBean implements Serializable {
    public String getTable() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table>");
        builder.append("<tr><td>X</td><td>Y</td><td>R</td><td>Результат</td></tr>");

        FacesContext context = FacesContext.getCurrentInstance();
        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        for (Result result: modelBean.getResults()) {
            builder.append("<tr><td>");
            builder.append(result.getPoint().getX());
            builder.append("</td><td>");
            builder.append(result.getPoint().getY());
            builder.append("</td><td>");
            builder.append(Arrays.toString(result.getRadiuses()));
            builder.append("</td><td></td></tr>");
        }

        builder.append("</table>");
        return builder.toString();
    }

    public String getSvg() {
        StringBuilder builder = new StringBuilder();
        builder.append("<svg width=\"600\" height=\"600\">");
        FacesContext context = FacesContext.getCurrentInstance();
        InputBean input = context.getApplication().evaluateExpressionGet(context, "#{input}", InputBean.class);

        int radiusCount = 0;

        for (int r = 5; r > 0; r--) {
            if (!input.getRs()[r - 1]) {
                continue;
            }

            radiusCount++;

            builder.append("<path d=\"");
            builder.append("M300 ");
            builder.append(300 - 50 * r);
            builder.append(" A");
            builder.append(50 * r);
            builder.append(' ');
            builder.append(50 * r);
            builder.append(" 0 0 1 ");
            builder.append(300 + 50 * r);
            builder.append(" 300 L");
            builder.append(300 + 25 * r);
            builder.append(" 300 L300 ");
            builder.append(300 + 25 * r);
            builder.append(" L300 300 L");
            builder.append(300 - 50 * r);
            builder.append(" 300 L");
            builder.append(300 - 50 * r);
            builder.append(' ');
            builder.append(300 - 25 * r);
            builder.append("L300 ");
            builder.append(300 - 25 * r);
            builder.append(" Z\" id=\"r");
            builder.append(r);
            builder.append("-path\" />");
        }

        builder.append("<line x1=\"300\" y1=\"10\" x2=\"300\" y2=\"600\" />");
        builder.append("<line x1=\"0\" y1=\"300\" x2=\"590\" y2=\"300\" />");
        builder.append("<polygon points=\"300,0 295,10 305,10\"></polygon>");
        builder.append("<polygon points=\"600,300 590,295 590,305\"></polygon>");
        builder.append("<text x=\"290\" y=\"10\" text-anchor=\"end\">y</text>");
        builder.append("<text x=\"595\" y=\"290\" text-anchor=\"end\">x</text>");

        for (int i = -5; i <= 5; i++) {
            builder.append("<circle cx=\"");
            builder.append(300 + 50 * i);
            builder.append("\" cy=\"300\" r=\"3\" />");
            builder.append("<text x=\"");
            builder.append(296 + 50 * i);
            builder.append("\" y=\"296\" text-anchor=\"end\">");
            builder.append(i);
            builder.append("</text>");
            if (i == 0) {
                continue;
            }
            builder.append("<circle cx=\"300\" cy=\"");
            builder.append(300 - 50 * i);
            builder.append("\" r=\"3\" />");
            builder.append("<text x=\"296\" y=\"");
            builder.append(296 - 50 * i);
            builder.append("\" text-anchor=\"end\">");
            builder.append(i);
            builder.append("</text>");
        }

        ModelBean modelBean = context.getApplication().evaluateExpressionGet(context, "#{model}", ModelBean.class);
        for (Result result: modelBean.getResults()) {
            Point point = result.getPoint();
            int matchCount = 0;
            for (int r = 1; r <= 5; r++) {
                if (input.getRs()[r - 1] && point.fallsInto(r)) {
                    matchCount++;
                }
            }

            builder.append("<circle cx=\"");
            builder.append(300 + 50 * point.getX());
            builder.append("\" cy=\"");
            builder.append(300 - 50 * point.getY());
            builder.append("\" r=\"5\" style=\"");
            builder.append(pointStyle(radiusCount, matchCount));
            builder.append("\" />");
        }

        builder.append("</svg>");
        return builder.toString();
    }

    private String pointStyle(int radiusCount, int matchCount) {
        return "stroke: #000; fill: #888";
    }
}
