package Firma;

public class Pielegniarka extends Pracownik {
	private int nadgodziny;

	public int getNadgodziny() {
		return nadgodziny;
	}

	public void setNadgodziny(int nadgodziny) {
		this.nadgodziny = nadgodziny;
	}

	public Pielegniarka(String name, String surname, int salary, int nadgodziny) {
		super(name, surname, salary);
		this.nadgodziny = nadgodziny;
	}
	
}
