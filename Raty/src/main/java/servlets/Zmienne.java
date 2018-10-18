package servlets;

public class Zmienne {
	float wnioskowanaKwotaKredytu = 0;
	int iloscRat = 0;
	float oprocentowanie = 0;
	float oplataStala = 0;
	String rodzajRat = "";

	public String getRodzajRat() {
		return rodzajRat;
	}

	public void setRodzajRat(String rodzajRat) {
		this.rodzajRat = rodzajRat;
	}

	public float getWnioskowanaKwotaKredytu() {
		return wnioskowanaKwotaKredytu;
	}

	public void setWnioskowanaKwotaKredytu(float wnioskowanaKwotaKredytu) {
		this.wnioskowanaKwotaKredytu = wnioskowanaKwotaKredytu;
	}

	public int getIloscRat() {
		return iloscRat;
	}

	public void setIloscRat(int iloscRat) {
		this.iloscRat = iloscRat;
	}

	public float getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(float oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public float getOplataStala() {
		return oplataStala;
	}

	public void setOplataStala(float oplataStala) {
		this.oplataStala = oplataStala;
	}

}
