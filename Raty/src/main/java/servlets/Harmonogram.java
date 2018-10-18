package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.DocumentException;

@WebServlet("/Harmonogram")
public class Harmonogram extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Harmonogram() {
		super();

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		Pdf pdf = new Pdf();
		pdf.dodajKrotke("Rata");
		pdf.dodajKrotke("Odsetki");
		pdf.dodajKrotke("Kapital");
		pdf.dodajKrotke("Oplata stala");
		pdf.dodajKrotke("Rata rowna");

		Zmienne zmienne = odbierzZmienne(request);

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
				pdf.dodajKrotke(i);
				pdf.dodajKrotke(zaokr(Odsetki));
				pdf.dodajKrotke(zaokr(Kapital));
				pdf.dodajKrotke(zaokr(zmienne.getOplataStala()));
				pdf.dodajKrotke(zaokr(rata + zmienne.getOplataStala()));
				TempWnioskowanaKwotaKredytu = TempWnioskowanaKwotaKredytu - (rata - Odsetki);
				sumaOdsetki = sumaOdsetki + Odsetki;
				sumaKapital = sumaKapital + Kapital;
			}
			pdf.dodajKrotke("Suma");
			pdf.dodajKrotke(zaokr(sumaOdsetki));
			pdf.dodajKrotke(zaokr(sumaKapital));
			pdf.dodajKrotke(zmienne.getOplataStala() * zmienne.getIloscRat());
			pdf.dodajKrotke(zaokr((sumaKapital + sumaOdsetki + (zmienne.getOplataStala() * zmienne.getIloscRat()))));
		} else if (zmienne.getRodzajRat().equals("malejaca")) {
			double TempWnioskowanaKwotaKredytu = zmienne.getWnioskowanaKwotaKredytu();
			double skladowaCzescRaty = zmienne.getWnioskowanaKwotaKredytu() / zmienne.getIloscRat();
			double sumaq = 0;
			for (int i = 1; i <= zmienne.getIloscRat(); i++) {
				double q = (1.0 / 12.0) * (zmienne.getOprocentowanie() / 100) * TempWnioskowanaKwotaKredytu;
				pdf.dodajKrotke(i);
				pdf.dodajKrotke(zaokr(q));
				pdf.dodajKrotke(zaokr(skladowaCzescRaty));
				pdf.dodajKrotke(zaokr(zmienne.getOplataStala()));
				pdf.dodajKrotke(zaokr(((skladowaCzescRaty + q + zmienne.getOplataStala()))));
				TempWnioskowanaKwotaKredytu = TempWnioskowanaKwotaKredytu - skladowaCzescRaty;
				sumaq = sumaq + q;
			}
			pdf.dodajKrotke("Suma");
			pdf.dodajKrotke(zaokr(sumaq));
			pdf.dodajKrotke(zmienne.getWnioskowanaKwotaKredytu());
			pdf.dodajKrotke(zmienne.getOplataStala() * zmienne.getIloscRat());
			pdf.dodajKrotke(zaokr((sumaq + zmienne.getWnioskowanaKwotaKredytu()
					+ (zmienne.getOplataStala() * zmienne.getIloscRat()))));
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
