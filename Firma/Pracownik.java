package Firma;

public class Pracownik {

	private String name;
	private String surname;
	private int salary;
	
	public Pracownik(String name, String surname, int salary) {
		super();
		this.name = name;
		this.surname = surname;
		this.salary = salary;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Pracownik [name=" + name + ", surname=" + surname + ", salary=" + salary + "]";
	}
	
	

}
