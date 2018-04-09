package Shop;

public class Promocja {

	static double znizka() {
		if (Cart.totalPrice() >= 300) {
			System.out.println("Obnizono cene o 5%!");
			return Cart.totalPrice() * 0.95;
		} else {
			return Cart.totalPrice();
		}
	}

	static void kubek() {
		if (Cart.totalPrice() >= 200) {
			Product kubek = new Product("Kubek promocyjny", 0);
			Cart cart = new Cart();
			cart.getItems().add(new CartItem(kubek));
		}
	}

	static void trzeciGratis() {
		double pom = 100000000;
		for (CartItem item : Cart.items) {
			if (item.getProduct().getPrice() < pom) {
				pom = item.getProduct().getPrice();
			}
		}
		for (CartItem item : Cart.items) {
			if (pom == item.getProduct().getPrice()) {
				System.out.println("Najtanszy produkt gratis: " + item.getProduct().getName());
				item.getProduct().setPrice(0);
			}
		}

	}

	static void czterdziesci(int a) {
		int i = 0;
		for (CartItem item : Cart.items) {
			i++;
			if (a == i) {
				System.out.println("Wybrales produkt o numerze: " + i + " i otrzymujesz na niego 40% znizki.");
				double pom = item.getProduct().getPrice() * 0.6;
				pom = Round.round(pom);
				item.getProduct().setPrice(pom);
			}
		}
	}
}
