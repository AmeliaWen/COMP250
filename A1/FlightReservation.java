
public class FlightReservation extends Reservation {
	//fields
	private Airport departure;
	private Airport arrival;
	//constructor
	public FlightReservation(String name, Airport d, Airport a) {
		super(name);
		this.arrival= a;
		this.departure= d;
		if(d.equals(a)) {
	
			throw new IllegalArgumentException ("two airports are same");
		}
		
	}
	@Override
	
	public int getCost() {
		double distance = (double)Airport.getDistance(departure, arrival);
		double fuelCost = (distance)*1.24/167.52*100.0;
		double airportFees = departure.getFees() + arrival.getFees();
		return (int) Math.ceil(fuelCost+airportFees+53.75*100);
		// rounded up to the nearest cent
		//return x;
	}
	@Override
	public boolean equals(Object a) {
		// TODO Auto-generated method stub
		if (a instanceof FlightReservation) {
			if (((FlightReservation)a).arrival.equals(this.arrival)&&((FlightReservation)a).departure.equals(this.departure)&&(((FlightReservation)a).reservationName().equals(this.reservationName()))){
				return true;
			}return false;
		
		}
		return false;
	}
	

}
