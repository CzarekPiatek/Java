package Pozyczki;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Dane {
	private String imie;
	private String nazwisko;
	private String pesel;
	private String nip;
	private String bankNumber;
	private Date urodzenie;
	private String plec;

	public Dane(String imie, String nazwisko, String pesel, String nip,
			String bankNumber, Date urodzenie, String plec) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.pesel = pesel;
		this.nip = nip;
		this.bankNumber = bankNumber;
		this.urodzenie = urodzenie;
		this.plec = plec;
	}

	public Dane() {
		
	}
	
	static private boolean walidator(String wzor,String patternString)
	{
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(wzor);
		if(!matcher.matches())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private boolean checkImieNazwisko(String dane)
	{
		if (dane == null)
		{
			return false;
		}
		else if(dane.length()<2)
		{
			return false;
		}
		else if(! walidator(dane,"^[a-zA-Z]*$"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	private boolean checkNum(String liczba,int ilosc)
	{
		if (liczba == null)
		{
			return false;
		}
		else if(liczba.length()>ilosc||liczba.length()<ilosc)
		{
			return false;
		}
		else if(! walidator(liczba,"^[1-9]*$"))
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		if (checkImieNazwisko(imie)==true)
		{
			this.imie = imie;
		}
		else
		{
			System.out.println("Zle wpisane imie: "+imie);
		}
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		if (checkImieNazwisko(nazwisko)==true)
		{
			this.nazwisko = nazwisko;
		}
		else
		{
			System.out.println("Zle wpisane nazwisko: "+nazwisko);
		}
	}

	
	@Override
	public String toString() {
		return "Dane [imie=" + imie + ", nazwisko=" + nazwisko + ", pesel="
				+ pesel + ", nip=" + nip + ", bankNumber=" + bankNumber
				+ ", urodzenie=" + urodzenie + ", plec=" + plec + "]";
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		Walidator.peselToTable(pesel);
		if (checkNum(pesel,11)== true && Walidator.checkSumPesel()==true)
		{
			this.pesel = pesel;
		}
		else
		{
			System.out.println("Zle wpisany pesel: "+pesel);
		}
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		Walidator.nipToTable(nip);
		if (checkNum(nip,10)== true && Walidator.checkSumNip() == true)
		{
			this.nip = nip;
		}
		else
		{
			System.out.println("Zle wpisany nip: "+nip);
		}
	}

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		if (checkNum(bankNumber,26)== true)
		{
			this.bankNumber = bankNumber;
		}
		else
		{
			System.out.println("Zle wpisany numer banku: "+bankNumber);
		}
	}

	public Date getUrodzenie() {
		return urodzenie;
	}

	public void setUrodzenie(Date urodzenie) {
		this.urodzenie = urodzenie;
	}

	public String getPlec() {
		return plec;
	}

	public void setPlec(String plec) {
		if(plec == "K" && Walidator.plec() == "K")
		{
			this.plec = plec;
		}
		else if(plec == "M" && Walidator.plec() == "M")
		{
			this.plec = plec;
		}
		else
		{
			System.out.println("Zle wpisana plec: "+plec);
		}
	}

}
