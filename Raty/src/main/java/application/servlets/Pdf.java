package application.servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Pdf {
    public static final String DEST = "src/main/webapp/harmonogram.pdf";
    Document document = new Document();
    PdfPTable table = new PdfPTable(5);


    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new Pdf().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException, DocumentException {
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        document.add(table);
        document.close();
    }

    public void addStart() {
        table.addCell("Rata");
        table.addCell("Odsetki");
        table.addCell("Kapital");
        table.addCell("Oplata stala");
        table.addCell("Rata rowna");
    }

    public void addTd(double i1, double i2, double i3, double i4, double i5) {
        table.addCell("" + i1);
        table.addCell("" + i2);
        table.addCell("" + i3);
        table.addCell("" + i4);
        table.addCell("" + i5);
    }

    public void addTd(String i1, double i2, double i3, double i4, double i5) {
        table.addCell("" + i1);
        table.addCell("" + i2);
        table.addCell("" + i3);
        table.addCell("" + i4);
        table.addCell("" + i5);
    }
}