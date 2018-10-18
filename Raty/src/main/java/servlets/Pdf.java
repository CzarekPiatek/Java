package servlets;

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

	public void dodajKrotke(double d) {
		table.addCell("" + d);
	}

	public void dodajKrotke(String string) {
		table.addCell(string);
	}
}