package application;

import application.servlets.Pdf;
import com.itextpdf.text.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

@WebServlet("/GeneratePDF")
public class GeneratePDF extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GeneratePDF() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        Pdf pdf = new Pdf();
        pdf.addStart();
        Bank bank = new ReceiveRequest().receive(request);
        Maths maths = new Maths(bank, pdf);
        if (bank.getLoanType().equals("stala")) {
            maths.constant(false);
        } else if (bank.getLoanType().equals("malejaca")) {
            maths.decreasing(false);
        } else {
            response.sendRedirect("/");
        }
        try {
            pdf.createPdf("src/main/webapp/pdf/" + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_"
                    + LocalTime.now().getSecond() + "_harmonogram.pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/pdf/" + LocalTime.now().getHour() + "_" + LocalTime.now().getMinute() + "_"
                + LocalTime.now().getSecond() + "_harmonogram.pdf");
    }
}
