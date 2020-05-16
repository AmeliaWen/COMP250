
public class Airport {
	//fields
	private int x;
	private int y;
	private int fees;
	//constructor
	public Airport (int a, int b, int c) {
		this.x=a;
		this.y = b;
		this.fees = c;
	}
	//other methods
	public int getFees() {
		return this.fees;
	}
	public static int getDistance (Airport a, Airport b) {
		return (int) Math.ceil(Math.sqrt(Math.pow(a.x-b.x,2) + Math.pow(a.y-b.y,2)));

	}

}
