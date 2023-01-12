package com.hdsaison.common.services.implementations;

import com.hdsaison.common.services.Excel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImpl implements Excel {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<Object> data = new ArrayList();
    private List<String> headers;
    private String sheetName = "export";

    public ExcelImpl() {
        this.workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        this.sheet = this.workbook.createSheet(this.sheetName);

        Row row = this.sheet.createRow(0);

        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        for (int i = 0; i < this.headers.size(); i ++) {
            this.createCell(row, i, this.headers.get(i), style);
        }
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        this.sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = this.workbook.createCellStyle();
        XSSFFont font = this.workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (int i=0; i < this.data.size(); i++) {
            List<Object> rowData = (List<Object>) this.data.get(i);
            Row row = this.sheet.createRow(rowCount++);
            for (int j = 0; j < rowData.size(); j++) {
                this.createCell(row, j, rowData.get(j), style);
            }
        }
    }

    @Override
    public String test() {
        return "Hello, Excel 2022";
    }

    @Override
    public ExcelImpl setSheetName(String sheetName) {
        this.sheetName = sheetName;
        return this;
    }

    @Override
    public ExcelImpl setHeaders(List<String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public ExcelImpl setData(List<Object> data) {
        this.data = data;
        return this;
    }

    @Override
    public void export(HttpServletResponse response) throws IOException {
        this.writeHeaderLine();
        this.writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        this.workbook.write(outputStream);
        this.workbook.close();

        outputStream.close();
    }
}
