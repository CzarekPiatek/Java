package Firma;

public class Firma {

	public static void main(String[] args) {
		Pracownik pracownicy[] = new Pracownik[3];
		String imiona[] = {"Cezary","Andrzej","Lukasz"};
		String nazwiska[] = {"Piatek","Moscibrodzki","Borzyszkowski"};
		int placa[] = {2000,5000,5300};
		
		for (int i = 0;i<pracownicy.length;i++)
		{
			pracownicy[i] = new Pracownik(imiona[i],nazwiska[i],placa[i]);
	
		}
		System.out.println("Pracownicy naszej firmy to:");
		for (Pracownik i : pracownicy)
		{
			System.out.println(i.getName()+" "+i.getSurname()+" "+i.getSalary());
		}
		Szef szef = new Szef("Kacper","Kowalski",7500,450);
		System.out.println("Nasz szef to: ");
		System.out.println(szef.getName()+" "+szef.getSurname()+" "+(szef.getSalary()+szef.getBonus()));
		Pielegniarka piel = new Pielegniarka("Grazyna", "Osowicka", 2800, 20);
		System.out.println("Pielegniarka ma "+piel.getNadgodziny()+" nadgodzin.");
	}

}
 