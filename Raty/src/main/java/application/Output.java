package application;

import application.servlets.Pdf;

import java.io.PrintWriter;

public class Output {
    public Output() {
    }

    public static void constOutInstallments(PrintWriter pri, int i1, double i2, double i3, double i4, double i5) {
        Validation val = new Validation();
        pri.println("<tr><td>" + i1 + "</td><td>" + val.ceil(i2) + "</td><td>" + val.ceil(i3) + "</td><td>"
                + val.ceil(i4) + "</td><td>" + val.ceil(i5) + "</td></tr>");
    }

    public static void constOutPdf(Pdf pdf, int i1, double i2, double i3, double i4, double i5) {
        Validation val = new Validation();
        pdf.addTd(val.ceil(i1), val.ceil(i2), val.ceil(i3), val.ceil(i4), val.ceil(i5));
    }

    public static void constOutInstallmentsEnd(PrintWriter pri, double i2, double i3, double i4, double i5) {
        Validation val = new Validation();
        pri.println("<tr><td>Suma</td><td>" + val.ceil(i2) + "</td><td>" + val.ceil(i3) + "</td><td>"
                + val.ceil(i4) + "</td><td>" + val.ceil(i5) + "</table>");
    }

    public static void constOutPdfEnd(Pdf pdf, double i2, double i3, double i4, double i5) {
        Validation val = new Validation();
        pdf.addTd("Suma", val.ceil(i2), val.ceil(i3), val.ceil(i4), val.ceil(i5));
    }
}
