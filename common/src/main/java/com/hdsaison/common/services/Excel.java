package com.hdsaison.common.services;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public interface Excel {
    public String test();
    public Excel setHeaders(List<String> headers);
    public Excel setSheetName(String sheetName);
    public Excel setData(List<Object> data);
    public void export(HttpServletResponse response) throws IOException;
}
