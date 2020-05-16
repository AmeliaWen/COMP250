
public class HotelReservation extends Reservation  {
	private Hotel place;
	private String type;
	private int number;
	private int pricenight;
	//constructor
	public HotelReservation (String name, Hotel h, String type, int number) {
		super(name);
		this.number= number;
		this.place= h;
		this.type= type;
		this.pricenight= h.reserveRoom(type);			
	}
	//other methods
	public int getNumOfNights () {
		return this.number;
	}
	public int getCost() {
		int price;
		price = this.pricenight*this.number;
		return price;
	}
	public boolean equals(Object a) {
		if (a instanceof HotelReservation ) {
			if (((HotelReservation)a).type.equals(this.type)&&((HotelReservation)a).reservationName().equals(this.reservationName())&&(((HotelReservation)a).number==this.number)&&(((HotelReservation)a).place.equals(this.place))&&(((HotelReservation)a).getCost()==this.getCost())) {
				return true;
			}
			return false;
		}
		return false;
		
	}
	
	

}
