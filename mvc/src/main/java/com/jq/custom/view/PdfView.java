package com.jq.custom.view;

import com.jq.dao.ViewResolverBean;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 11:08
 */
public class PdfView extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document,
                                    PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ViewResolverBean resolverBean = (ViewResolverBean) model.get("resolverBean");

        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.getDefaultCell().setBackgroundColor(Color.lightGray);

        table.addCell("Name");
        table.addCell("Flavor");
        table.addCell("Toppings");

        table.addCell(resolverBean.getName());
        table.addCell(String.valueOf(resolverBean.getAge()));

        StringBuffer toppings = new StringBuffer("");
        for (String topping : resolverBean.getLikes()) {
            toppings.append(topping);
            toppings.append(" ");
        }
        table.addCell(toppings.toString());
        document.add(table);

    }
}
