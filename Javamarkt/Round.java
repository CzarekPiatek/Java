package Shop;

public class Round {
	public static double round(double prom) {
		prom *= 100;
		prom = Math.round(prom);
		prom /= 100;
		return prom;
	}

}
