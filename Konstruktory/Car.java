
public class Car 
{
	String marka;
	boolean czynowy;
	int przebieg;
	double cena;
	void PrintCar()
	{
		System.out.println("Marka: " + marka);
		System.out.println("Nowy: " + czynowy);
		System.out.println("Przebieg :" + przebieg);
		System.out.println("Cena :" + cena);
	}
	Car()
	{
		System.out.println("Nowy konstruktor Car();");
	}
	Car(String marka)
	{
		this();
		this.marka = marka;
	}	
	Car(String marka, boolean b, int i, double d) 
	{
		this(marka);
		this.czynowy = b;
		this.przebieg = i;
		this.cena = d;	
	}
}
