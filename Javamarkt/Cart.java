package Shop;

import java.util.ArrayList;

public class Cart {

	static ArrayList<CartItem> items = new ArrayList<CartItem>();
	
	static double totalPrice() {
		double sum = 0;
		for(CartItem item : items)
		{
			sum += item.getProduct().getPrice();
		}
		return sum;
	}

	public ArrayList<CartItem> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Cart [items=" + items + "]";
	}
	
	
}
