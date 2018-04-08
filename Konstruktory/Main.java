
public class Main 
{
	public static void main(String[] args) 
	{
		Car car = new Car();
		car.marka = "Seat Leon";
		car.czynowy = false;
		car.przebieg = 252000;
		car.cena = 16900;
		car.PrintCar();
		
		Car car2 = new Car("Kia Ceed", true, 0, 75900.0);
		car2.PrintCar();
		
		
		Bike b1 = new Bike("niebieski", 14, "dzieciecy", 500);
		Bike b2 = new Bike("czarny", 29);
		Bike b3 = new Bike("rudy");
		Bike b4 = new Bike();
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(b3);
		System.out.println(b4);
	}
}


