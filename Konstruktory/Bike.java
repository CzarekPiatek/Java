
public class Bike 
{
	private String color;
	private int inches;
	private String type;
	private int cena; 
	
	public Bike(String color, int inches, String type, int cena) 
	{
		this.color = color;
		this.inches = inches;
		this.type = type;
		this.cena = cena;
	}
	
	public Bike(String color, int inches)
	{
		this(color,inches,"Gorski", 1000);
	}
	
	public Bike(String color)
	{
		this(color, 26);
	}
	
	public Bike()
	{
		this("czerwony");
	}
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getInches() {
		return inches;
	}

	public void setInches(int inches) {
		this.inches = inches;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Bike [color=" + color + ", inches=" + inches + ", type=" + type
				+ ", cena=" + cena + "]";
	}

	
	
}

