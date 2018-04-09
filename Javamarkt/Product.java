package Shop;

public class Product {
	private String name;
	private double price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public Product(String name, double price) {
		super();
		this.name = name;
		this.price = price;
		System.out.println("Dodano produkt: " + name + " Cena: " + price + " zl");
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", price=" + price + "]";
	}

}
