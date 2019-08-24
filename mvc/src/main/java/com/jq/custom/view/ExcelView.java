package com.jq.custom.view;

import com.jq.dao.ViewResolverBean;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-24 11:13
 */
public class ExcelView extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
                                      HttpServletRequest request, HttpServletResponse response) throws Exception {
        ViewResolverBean resolverBean = (ViewResolverBean) model.get("resolverBean");

        Sheet sheet = workbook.createSheet("sheet 1");
        CellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        Row row = null;
        Cell cell = null;
        int rowCount = 0;
        int colCount = 0;

        // Create header cells
        row = sheet.createRow(rowCount++);

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Name");

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Flavor");

        cell = row.createCell(colCount++);
        cell.setCellStyle(style);
        cell.setCellValue("Toppings");

        // Create data cells
        row = sheet.createRow(rowCount++);
        colCount = 0;
        row.createCell(colCount++).setCellValue(resolverBean.getName());
        row.createCell(colCount++).setCellValue(resolverBean.getAge());

        StringBuffer toppings = new StringBuffer("");
        for (String topping : resolverBean.getLikes()) {
            toppings.append(topping);
            toppings.append(" ");
        }
        row.createCell(colCount++).setCellValue(toppings.toString());

        for (int i = 0; i < 3; i++)
            sheet.autoSizeColumn(i, true);

    }
}
