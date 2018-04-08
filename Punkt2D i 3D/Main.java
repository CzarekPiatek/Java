package Punkt2D;

public class Main {

	public static void main(String[] args) {
		Punkt2D pkt1 = new Punkt2D(5,10);
		Punkt2D pkt2 = new Punkt2D();
		System.out.println("Punkt nr1: "+pkt1.getX()+" "+pkt1.getY());
		System.out.println("Punkt nr2: "+pkt2.getX()+" "+pkt2.getY());
		Punkt3D pkt3 = new Punkt3D();
		Punkt3D pkt4 = new Punkt3D(1,2,3);
		System.out.println("Punkt nr3: "+pkt3.getX()+" "+pkt3.getY()+" "+pkt3.getZ());
		System.out.println("Punkt nr4: "+pkt4.getX()+" "+pkt4.getY()+" "+pkt4.getZ());
	}

}
