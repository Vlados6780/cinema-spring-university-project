package com.example.demo.controller;

import com.example.demo.dao.MovieDAO;
import com.example.demo.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequiredArgsConstructor
@RequestMapping("excel")
public class ExcelController {

    private final ExcelService excelService;

    @GetMapping
    public String getExcelPage(){
        return "excel/excel";
    }

    @PostMapping("/save")
    public ResponseEntity<byte[]> saveExcelFile() throws IOException {

        byte[] bytes = excelService.generateExcel();
        String encodedFileName = URLEncoder.encode("Cinema"  + ".xlsx", StandardCharsets.UTF_8.toString());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + encodedFileName)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(bytes);
    }

}
