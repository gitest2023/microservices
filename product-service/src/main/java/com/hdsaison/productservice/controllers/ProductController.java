package com.hdsaison.productservice.controllers;

import com.hdsaison.common.services.Excel;
import com.hdsaison.common.services.Mailer;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RequestMapping("/api/products")
@RestController
public class ProductController {
    private static final List<Object> employees;

    static {
        employees = new ArrayList<>();
        employees.add("test");
        employees.add("demo");
    }

    @Autowired
    private Excel excel;

    @Autowired
    private Mailer mailer;

    @GetMapping
    public String index() {
        return "Hello, product service";
    }

    @GetMapping("/json")
    public ResponseEntity<List<Object>> testHttp() {
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/sendmail")
    public String sendmail() {
        this.mailer.send("gitworktest@gmail.com", "This is test mail", "This is a text body");
        return "Sent";
    }

    @GetMapping("/export-excel")
    public void exportExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        var headers = Arrays.asList("User ID", "E-mail", "Full Name", "Role", "Enabled");
        var data = new ArrayList<>();
        data.add(Arrays.asList(1, "a@gmail.com", "Name 1", "Dev", 0));
        data.add(Arrays.asList(2, "b@gmail.com", "Name 2", "Admin", 1));
        data.add(Arrays.asList(3, "c@gmail.com", "Name 3", "Leader", 1));

        this.excel.setHeaders(headers).setData(data).export(response);
    }
}
