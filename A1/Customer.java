
public class Customer {
	private String name;
	private int balance;
	private Basket reservations;
	//constructor 
	public Customer (String name, int balance) {
		this.name = name;
		this.balance= balance;
		this.reservations = new Basket();
	}
	public String getName () {
		return this.name;
	}
	public int getBalance () {
		return this.balance;
	}
	public Basket getBasket () {
		return this.reservations;
	}
	public int addFunds (int amount) {
		if (amount <0) {
			throw new IllegalArgumentException ("non valid amount to add");
		}
		this.balance+=amount;
		return this.balance;
	}
	public int addToBasket (Reservation res) {
		if (res.reservationName().equalsIgnoreCase(this.name)) {
			this.reservations.add(res);
			return this.reservations.getNumOfReservations();
		}else {
			throw new IllegalArgumentException ("no match with the customer name");
		}
	}
	public int addToBasket (Hotel h, String type, int num, boolean breakfast) {
		if (! breakfast) {
		HotelReservation newres = new HotelReservation (this.name, h, type, num);
		this.reservations.add(newres);
		}else {
			BnBReservation newres = new BnBReservation (this.name, h, type, num);
			this.reservations.add(newres);
		}
		return this.reservations.getNumOfReservations();
		}
	public int addToBasket (Airport a, Airport b) {
		Reservation newres = new FlightReservation (this.name, a, b);
		this.reservations.add(newres);
		return this.reservations.getNumOfReservations();
	}
	public boolean removeFromBasket (Reservation re) {
				return this.reservations.remove(re);
	}
	public int checkOut () {
		int result;
		if (this.reservations.getTotalCost() > this.balance) {
			throw new IllegalArgumentException ();
		}else {
			result = this.balance - this.reservations.getTotalCost();
			this.reservations.clear();
			return result;
		}
		
	}

}
