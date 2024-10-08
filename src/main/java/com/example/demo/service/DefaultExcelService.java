package com.example.demo.service;

import com.example.demo.controller.payload.MoviesWithActors;
import com.example.demo.dao.MovieDAO;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultExcelService implements ExcelService {

    private final MovieDAO movieDAO;

    public byte[] generateExcel() throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Data of cinema" );

            Row row = sheet.createRow(0);
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 4000);
            sheet.setColumnWidth(3, 4000);

            CellStyle centeredStyle = workbook.createCellStyle();
            centeredStyle.setAlignment(HorizontalAlignment.CENTER);
            centeredStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            centeredStyle.setBorderTop(BorderStyle.THICK);
            centeredStyle.setBorderBottom(BorderStyle.THICK);
            centeredStyle.setBorderLeft(BorderStyle.THICK);
            centeredStyle.setBorderRight(BorderStyle.THICK);

            Cell actorName = row.createCell(0);
            actorName.setCellValue("Actor name");
            actorName.setCellStyle(centeredStyle);
            Cell age = row.createCell(1);
            age.setCellValue("Age");
            age.setCellStyle(centeredStyle);
            Cell movieName = row.createCell(2);
            movieName.setCellValue("Movie name");
            movieName.setCellStyle(centeredStyle);
            Cell yearOfProduction = row.createCell(3);
            yearOfProduction.setCellValue("Year of production");
            yearOfProduction.setCellStyle(centeredStyle);

            int rowNum = 1;
            List<MoviesWithActors> moviesWithActors = movieDAO.getAllMoviesWithActors();
            for (MoviesWithActors movieWithActor : moviesWithActors){

                Row row1 = sheet.createRow(rowNum);
                Cell actorName1 = row1.createCell(0);
                actorName1.setCellValue(movieWithActor.getActorName());
                actorName1.setCellStyle(centeredStyle);
                Cell age1 = row1.createCell(1);
                age1.setCellValue(movieWithActor.getActorAge());
                age1.setCellStyle(centeredStyle);
                Cell movieName1 = row1.createCell(2);
                movieName1.setCellValue(movieWithActor.getMovieName());
                movieName1.setCellStyle(centeredStyle);
                Cell yearOfProduction1 = row1.createCell(3);
                yearOfProduction1.setCellValue(movieWithActor.getYearOfProduction());
                yearOfProduction1.setCellStyle(centeredStyle);

                rowNum++;
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }
}
