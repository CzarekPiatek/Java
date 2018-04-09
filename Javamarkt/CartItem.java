package Shop;

public class CartItem {
	private Product product;
	private double currentPrice;

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	public CartItem(Product product) {
		super();
		this.product = product;
		this.currentPrice = product.getPrice();
	}

	@Override
	public String toString() {
		return "CartItem [product=" + product + ", currentPrice=" + currentPrice + "]";
	}

}
