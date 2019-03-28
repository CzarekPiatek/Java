package application.servlets;

import application.Maths;
import application.ReceiveRequest;
import application.Validation;
import application.Bank;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Installments")
public class Installments extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Installments() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bank bank = new ReceiveRequest().receive(request);
        response.setContentType("text/html");
        PrintWriter pri = response.getWriter();
        Validation val = new Validation();
        Maths maths = new Maths(bank, pri);

        if (val.equals(request)) {
            response.sendRedirect("/");
        }
        try {
            bank = new ReceiveRequest().receive(request);
        } catch (NumberFormatException e) {
            response.sendRedirect("/");
        }
        pri.println(
                "<table border=1><tr><td>Rata</td><td>Odsetki</td><td>Kapital</td><td>Oplata stala</td><td>Rata rowna</td></tr>");

        if (bank.getLoanType().equals("stala")) {
            maths.constant(true);
        } else if (bank.getLoanType().equals("malejaca")) {
            maths.decreasing(true);
        } else {
            response.sendRedirect("/");
        }
        pri.println("<form action=\"GeneratePDF\" method=\"post\">" + "<input type=\"hidden\" value='"
                + bank.getAmount() + "' name=\"wnioskowanaKwotaKredytu\">"
                + "<input type=\"hidden\" value='" + bank.getNumberOfInstallments() + "' name=\"iloscRat\">"
                + "<input type=\"hidden\" value='" + bank.getInterest() + "' name=\"oprocentowanie\">"
                + "<input type=\"hidden\" value='" + bank.getFixedFee() + "' name=\"oplataStala\">"
                + "<input type=\"hidden\" value='" + bank.getLoanType() + "' name=\"rodzajRat\">"
                + "<input type=\"submit\" value=\"Generuj PDF\">" + "</form>");

    }

}
