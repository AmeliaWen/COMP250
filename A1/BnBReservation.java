
public class BnBReservation extends HotelReservation{
	public BnBReservation (String name, Hotel h, String type, int number) {
      super(name, h, type, number);
		
	}
	public int getCost () {
		int total;
		total = super.getNumOfNights()*1000+ super.getCost();
		return total;
	}

}
