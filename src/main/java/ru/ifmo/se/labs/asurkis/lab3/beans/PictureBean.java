package ru.ifmo.se.labs.asurkis.lab3.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;

@ManagedBean(name="picture")
@ViewScoped
public class PictureBean {
    public String getSvg() {
        StringBuilder builder = new StringBuilder();
        builder.append("<svg width=\"600\" height=\"600\">");
        FacesContext context = FacesContext.getCurrentInstance();
        InputBean input = context.getApplication().evaluateExpressionGet(context, "#{input}", InputBean.class);
        for (int r = 5; r > 0; r--) {
            if (!input.getRs()[r - 1]) {
                continue;
            }
            builder.append("<path d=\"")
                    .append("M300 ")
                    .append(300 - 50 * r)
                    .append(" A")
                    .append(50 * r)
                    .append(' ')
                    .append(50 * r)
                    .append(" 0 0 1 ")
                    .append(300 + 50 * r)
                    .append(" 300 L")
                    .append(300 + 25 * r)
                    .append(" 300 L300 ")
                    .append(300 + 25 * r)
                    .append(" L300 300 L")
                    .append(300 - 50 * r)
                    .append(" 300 L")
                    .append(300 - 50 * r)
                    .append(' ')
                    .append(300 - 25 * r)
                    .append("L300 ")
                    .append(300 - 25 * r)
                    .append(" Z\" id=\"r")
                    .append(r)
                    .append("-path\" />");
        }

        builder.append("<line x1=\"300\" y1=\"10\" x2=\"300\" y2=\"600\" />");
        builder.append("<line x1=\"0\" y1=\"300\" x2=\"590\" y2=\"300\" />");
        builder.append("<polygon points=\"300,0 295,10 305,10\"></polygon>");
        builder.append("<polygon points=\"600,300 590,295 590,305\"></polygon>");
        builder.append("<text x=\"290\" y=\"10\" text-anchor=\"end\">y</text>");
        builder.append("<text x=\"595\" y=\"290\" text-anchor=\"end\">x</text>");

        for (int i = -5; i <= 5; i++) {
            builder.append("<circle cx=\"")
                    .append(300 + 50 * i)
                    .append("\" cy=\"300\" r=\"3\" />");
            builder.append("<text x=\"")
                    .append(296 + 50 * i)
                    .append("\" y=\"296\" text-anchor=\"end\">")
                    .append(i)
                    .append("</text>");
            if (i == 0) {
                continue;
            }
            builder.append("<circle cx=\"300\" cy=\"")
                    .append(300 - 50 * i)
                    .append("\" r=\"3\" />");
            builder.append("<text x=\"296\" y=\"")
                    .append(296 - 50 * i)
                    .append("\" text-anchor=\"end\">")
                    .append(i)
                    .append("</text>");
        }
        builder.append("</svg>");
        return builder.toString();
    }
}
