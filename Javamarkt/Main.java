package Shop;

public class Main {

	public static void main(String[] args) {

		System.out.println("Witamy w sklepie Javamarkt!");
		System.out.println("W sklepie sa nastepujace promocje:");
		System.out.println("Jesli cena zakupow przekroczy 300zl - obnizymy cene o 5%.");
		System.out.println("Jesli cena zakupow przekroczy 200zl - otrzymaja panstwo kubek promocyjny.");
		Product maslo = new Product("Maslo", 50.50);
		Product bulka = new Product("Bulka", 200.85);
		Product rogalik = new Product("Rogalik", 25.20);
		Product precel = new Product("Precel", 175.20);
		Cart cart = new Cart();
		cart.getItems().add(new CartItem(maslo));
		cart.getItems().add(new CartItem(bulka));
		cart.getItems().add(new CartItem(rogalik));
		cart.getItems().add(new CartItem(precel));
		double price = Round.round(Cart.totalPrice());
		System.out.println("Cena przed promocja: " + price + " zl");
		Promocja.znizka();
		Promocja.trzeciGratis();
		Promocja.kubek();
		Promocja.czterdziesci(2);
		System.out.println(cart.getItems());
		price = Round.round(Cart.totalPrice());
		System.out.println("Cena po wszystkich promocjach: " + price + " zl");
	}

}
