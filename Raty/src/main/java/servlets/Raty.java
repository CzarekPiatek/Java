package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Raty")
public class Raty extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Raty() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private Zmienne odbierzZmienne(HttpServletRequest request) {
		Zmienne zmienne = new Zmienne();
		zmienne.setWnioskowanaKwotaKredytu(Float.parseFloat(request.getParameter("wnioskowanaKwotaKredytu")));
		zmienne.setOplataStala(Float.parseFloat(request.getParameter("oplataStala")));
		zmienne.setOprocentowanie(Float.parseFloat(request.getParameter("oprocentowanie")));
		zmienne.setIloscRat(Integer.parseInt(request.getParameter("iloscRat")));
		zmienne.setRodzajRat(request.getParameter("rodzajRat"));
		return zmienne;
	}

	private double zaokr(double zaokr) {
		return Math.ceil(zaokr * 100) / 100;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter wyp = response.getWriter();

		if (request.getParameter("wnioskowanaKwotaKredytu").equals("0") || request.getParameter("iloscRat").equals("0")
				|| request.getParameter("wnioskowanaKwotaKredytu").isEmpty()
				|| request.getParameter("iloscRat").isEmpty() || request.getParameter("oprocentowanie").equals("0")
				|| request.getParameter("oplataStala").isEmpty() || request.getParameter("rodzajRat").equals("")) {
			response.sendRedirect("/");
		}
		try {
			Zmienne zmienne = odbierzZmienne(request);
			zmienne.getIloscRat();
			zmienne.getOplataStala();
			zmienne.getOprocentowanie();
			zmienne.getRodzajRat();
			zmienne.getWnioskowanaKwotaKredytu();
		} catch (NumberFormatException e) {
			response.sendRedirect("/");
		}

		Zmienne zmienne = odbierzZmienne(request);

		wyp.println(
				"<table border=1><tr><td>Rata</td><td>Odsetki</td><td>Kapital</td><td>Oplata stala</td><td>Rata rowna</td></tr>");

		if (zmienne.getRodzajRat().equals("stala")) {

			double q = 1 + ((1 / 12.0) * (zmienne.getOprocentowanie() / 100));
			double TempWnioskowanaKwotaKredytu = zmienne.getWnioskowanaKwotaKredytu();
			double rata = zmienne.getWnioskowanaKwotaKredytu() * (Math.pow(q, zmienne.getIloscRat()) * (q - 1))
					/ ((Math.pow(q, zmienne.getIloscRat())) - 1);
			double sumaOdsetki = 0;
			double sumaKapital = 0;
			for (int i = 1; i <= zmienne.getIloscRat(); i++) {
				double Odsetki = TempWnioskowanaKwotaKredytu * ((zmienne.getOprocentowanie() / 100) / 12);
				double Kapital = rata - Odsetki;
				wyp.println("<tr><td>" + i + "</td>");
				wyp.println("<td>" + zaokr(Odsetki) + "</td>");
				wyp.println("<td>" + zaokr(Kapital) + "</td>");
				wyp.println("<td>" + zaokr(zmienne.getOplataStala()) + "</td>");
				wyp.println("<td>" + zaokr(rata + zmienne.getOplataStala()) + "</td></tr>");
				TempWnioskowanaKwotaKredytu = TempWnioskowanaKwotaKredytu - (rata - Odsetki);
				sumaOdsetki = sumaOdsetki + Odsetki;
				sumaKapital = sumaKapital + Kapital;
			}
			wyp.println("<tr><td>Suma</td><td>" + zaokr(sumaOdsetki) + "</td><td>" + zaokr(sumaKapital) + "</td><td>"
					+ zmienne.getOplataStala() * zmienne.getIloscRat() + "</td><td>"
					+ zaokr((sumaKapital + sumaOdsetki + (zmienne.getOplataStala() * zmienne.getIloscRat())))
					+ "</table>");

		} else if (zmienne.getRodzajRat().equals("malejaca")) {
			double TempWnioskowanaKwotaKredytu = zmienne.getWnioskowanaKwotaKredytu();
			double skladowaCzescRaty = zmienne.getWnioskowanaKwotaKredytu() / zmienne.getIloscRat();
			double sumaq = 0;
			for (int i = 1; i <= zmienne.getIloscRat(); i++) {
				double q = (1.0 / 12.0) * (zmienne.getOprocentowanie() / 100) * TempWnioskowanaKwotaKredytu;
				wyp.println("<tr><td>" + i + "</td>");
				wyp.println("<td>" + zaokr(q) + "</td>");
				wyp.println("<td>" + zaokr(skladowaCzescRaty) + "</td>");
				wyp.println("<td>" + zaokr(zmienne.getOplataStala()) + "</td>");
				wyp.println("<td>" + zaokr(((skladowaCzescRaty + q + zmienne.getOplataStala()))) + "</td></tr>");
				TempWnioskowanaKwotaKredytu = TempWnioskowanaKwotaKredytu - skladowaCzescRaty;
				sumaq = sumaq + q;
			}
			wyp.println("<tr><td>Suma</td><td>" + zaokr(sumaq) + "</td><td>" + zmienne.getWnioskowanaKwotaKredytu()
					+ "</td><td>" + zmienne.getOplataStala() * zmienne.getIloscRat() + "</td><td>"
					+ zaokr((sumaq + zmienne.getWnioskowanaKwotaKredytu()
							+ (zmienne.getOplataStala() * zmienne.getIloscRat())))
					+ "</table>");
		} else {
			response.sendRedirect("/");
		}
		wyp.println("<form action=\"Harmonogram\" method=\"post\">" + "<input type=\"hidden\" value='"
				+ zmienne.getWnioskowanaKwotaKredytu() + "' name=\"wnioskowanaKwotaKredytu\">"
				+ "<input type=\"hidden\" value='" + zmienne.getIloscRat() + "' name=\"iloscRat\">"
				+ "<input type=\"hidden\" value='" + zmienne.getOprocentowanie() + "' name=\"oprocentowanie\">"
				+ "<input type=\"hidden\" value='" + zmienne.getOplataStala() + "' name=\"oplataStala\">"
				+ "<input type=\"hidden\" value='" + zmienne.getRodzajRat() + "' name=\"rodzajRat\">"
				+ "<input type=\"submit\" value=\"Generuj PDF\">" + "</form>");

	}

}
