package Firma;

public class Szef extends Pracownik {
	private int bonus;

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public Szef(String name, String surname, int salary, int bonus) {
		super(name, surname, salary);
		this.bonus = bonus;
	}
	
	
}
