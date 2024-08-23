package com.web.service;

import com.web.model.ProductoEntity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelExportService {

    public ByteArrayInputStream exportProductosToExcel(List<ProductoEntity> productos) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Productos");

            // Crear encabezados
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("SKU");
            headerRow.createCell(2).setCellValue("Nombre");
            headerRow.createCell(3).setCellValue("Categoría");
            headerRow.createCell(4).setCellValue("Descripción");
            headerRow.createCell(5).setCellValue("Nro. Caja");
            headerRow.createCell(6).setCellValue("Cantidad");
            headerRow.createCell(7).setCellValue("Talla");
            headerRow.createCell(8).setCellValue("Precio");
            headerRow.createCell(9).setCellValue("Fecha de Creaciòn");



            // Llenar datos
            int rowIdx = 1;
            for (ProductoEntity producto : productos) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(producto.getIdprenda());
                row.createCell(1).setCellValue(producto.getSku());
                row.createCell(2).setCellValue(producto.getNombrepro());
                row.createCell(3).setCellValue(producto.getCategoria());
                row.createCell(4).setCellValue(producto.getDescripcion());
                row.createCell(5).setCellValue(producto.getNrocaja());
                row.createCell(6).setCellValue(producto.getCantidad());
                row.createCell(7).setCellValue(producto.getTalla());
                row.createCell(8).setCellValue(producto.getPrecio());
                row.createCell(9).setCellValue(producto.getFechaemision());


            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}