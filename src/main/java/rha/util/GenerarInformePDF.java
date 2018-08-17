package rha.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import rha.model.Cura;
import rha.model.Proceso;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenerarInformePDF {

    public static ByteArrayInputStream informeProceso(Proceso proceso) {

    	String IMG = "src/main/resources/logo.png";
    	Document document = new Document(PageSize.A4);
        document.setMargins(60, 30, 30, 30);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        try {
        	Image img = Image.getInstance(IMG);
        	img.setAbsolutePosition(60, 750);
        	img.scalePercent(45);
        	            
        	// Título del documento
        	Font f=new Font(FontFamily.TIMES_ROMAN, 25.0f, Font.BOLD, BaseColor.BLUE);
        	String titulo = "Informe Médico de Proceso\n\n";
            Paragraph p1=new Paragraph(titulo, f);
            p1.setAlignment(Element.ALIGN_CENTER);
            
            Font fuenteCabecera = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            
            // Tabla para definir el proceso
            PdfPTable tabla1 = new PdfPTable(2);
            tabla1.setWidthPercentage(100);
            tabla1.setWidths(new int[]{3, 6});
            
            PdfPCell celdaTabla1Titulo;
            celdaTabla1Titulo = new PdfPCell(new Phrase("Paciente", fuenteCabecera));
            celdaTabla1Titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTabla1Titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1Titulo);
            
            PdfPCell celdaTabla1;
            celdaTabla1 = new PdfPCell(new Phrase(proceso.getPaciente().getFullName()));
            celdaTabla1.setVerticalAlignment(Element.ALIGN_TOP);
            celdaTabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1);
            
            celdaTabla1Titulo = new PdfPCell(new Phrase("Primera visita", fuenteCabecera));
            celdaTabla1Titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTabla1Titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1Titulo);
            
            celdaTabla1 = new PdfPCell(new Phrase(Fecha.fechaHoraSPshort(proceso.getCreacion())));
            celdaTabla1.setVerticalAlignment(Element.ALIGN_TOP);
            celdaTabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1);
            
            celdaTabla1Titulo = new PdfPCell(new Phrase("Anamnesis", fuenteCabecera));
            celdaTabla1Titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTabla1Titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1Titulo);
            
            celdaTabla1 = new PdfPCell(new Phrase(proceso.getAnamnesis()));
            celdaTabla1.setVerticalAlignment(Element.ALIGN_TOP);
            celdaTabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1);
            
            celdaTabla1Titulo = new PdfPCell(new Phrase("Diagnóstico", fuenteCabecera));
            celdaTabla1Titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTabla1Titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1Titulo);
            
            celdaTabla1 = new PdfPCell(new Phrase(proceso.getDiagnostico().getNombre()));
            celdaTabla1.setVerticalAlignment(Element.ALIGN_TOP);
            celdaTabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1);
            
            celdaTabla1Titulo = new PdfPCell(new Phrase("Procedimiento realizado", fuenteCabecera));
            celdaTabla1Titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTabla1Titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1Titulo);
            
            celdaTabla1 = new PdfPCell(new Phrase(proceso.getProcedimiento().getNombre()));
            celdaTabla1.setVerticalAlignment(Element.ALIGN_TOP);
            celdaTabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1);    
            
            celdaTabla1Titulo = new PdfPCell(new Phrase("Observaciones", fuenteCabecera));
            celdaTabla1Titulo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            celdaTabla1Titulo.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1Titulo);
            
            celdaTabla1 = new PdfPCell(new Phrase(proceso.getObservaciones()));
            celdaTabla1.setVerticalAlignment(Element.ALIGN_TOP);
            celdaTabla1.setHorizontalAlignment(Element.ALIGN_LEFT);
            tabla1.addCell(celdaTabla1); 
            
            // Parrafo para dar paso a las curas
            String cadena = "\n\nA continuación las sucesivas curas asociadas al anterior proceso:\n\n";
            Paragraph p2=new Paragraph(cadena);
            p2.setAlignment(Element.ALIGN_LEFT);
        	
            
            // Tabla con el contenido de las curas asociadas al proceso
            PdfPTable tabla2 = new PdfPTable(4);
            tabla2.setWidthPercentage(100);
            tabla2.setWidths(new int[]{3, 3, 3, 3});

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Fecha", fuenteCabecera));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Evolución", fuenteCabecera));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Tratamiento", fuenteCabecera));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("Recomendaciones", fuenteCabecera));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            tabla2.addCell(hcell);

            List<Cura> curas = proceso.getCuras();
            for (Cura cura : curas) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(Fecha.fechaHoraSPshort(cura.getCreacion())));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                tabla2.addCell(cell);

                cell = new PdfPCell(new Phrase(cura.getEvolucion()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                tabla2.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(cura.getTratamiento())));
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                cell.setPaddingRight(5);
                tabla2.addCell(cell);
                
                cell = new PdfPCell(new Phrase(String.valueOf(cura.getRecomendaciones())));
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
                cell.setPaddingRight(5);
                tabla2.addCell(cell);
                
                
            }
            
            // Parrafo para dar paso al Centro y Fecha
            String centro = "\n" + proceso.getPaciente().getCentroActual().getNombre();
            centro += "\n" + proceso.getPaciente().getCentroActual().getDireccion();
            centro += "\n" + proceso.getPaciente().getCentroActual().getTelefono();
            centro += "\n\n" + "Informe emitido el: " + Fecha.fechaHoraSP(new Date());
            
            Paragraph p3=new Paragraph(centro);
            p3.setAlignment(Element.ALIGN_LEFT);

            PdfWriter.getInstance(document, out);
            
            document.open();
            
            document.add(img);
            document.add(p1);
            document.add(tabla1);
            document.add(p2);
            document.add(tabla2);
            document.add(p3);
            
            document.close();
            
        } catch (DocumentException ex) {
        
            Logger.getLogger(GenerarInformePDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return new ByteArrayInputStream(out.toByteArray());
    }
}
