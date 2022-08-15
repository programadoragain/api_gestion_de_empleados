package com.API.gestionempleados.Utils.Reportes;


import com.API.gestionempleados.Entities.Empleado;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class EmpleadoExportPDF {

    private List<Empleado> listaEmpleados;

    private void cabeceraTabla(PdfPTable tabla) {
        PdfPCell celda= new PdfPCell();
        Font font= FontFactory.getFont(FontFactory.HELVETICA);

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);
        font.setColor(Color.WHITE);

        celda.setPhrase(new Phrase("Id", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Nombre", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Apellido", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Fecha Nac.", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Email", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Telefono", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Salario", font));
        tabla.addCell(celda);
        celda.setPhrase(new Phrase("Sexo", font));
        tabla.addCell(celda);
    }

    private void datosTabla (PdfPTable tabla) {
        for (Empleado e: listaEmpleados) {
            tabla.addCell(String.valueOf(e.getId()));
            tabla.addCell(e.getNombre());
            tabla.addCell(e.getApellido());
            tabla.addCell(e.getFechaNacimiento().toString());
            tabla.addCell(e.getEmail());
            tabla.addCell(e.getTelefono());
            tabla.addCell(String.valueOf(e.getSueldo()));
            tabla.addCell(e.getSexo());
        }
    }

    public void exportar(HttpServletResponse response) throws IOException {
        Document documento= new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();
        Font font= FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
        font.setSize(18);

        Paragraph titulo= new Paragraph("Lista de empleados", font);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);

        PdfPTable tabla= new PdfPTable(8);
        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[] {1f,2.3f,2.3f,3f,6f,3.5f,3f,2.3f});
        tabla.setWidthPercentage(110);

        cabeceraTabla(tabla);
        datosTabla(tabla);
        documento.add(tabla);
        documento.close();
    }

}
