
public class Basket {
	private Reservation [] reservation;
	public Basket () {
		this.reservation= new Reservation [0];
		
	}
	public Reservation [] getProducts() {
		Reservation [] a = new Reservation[this.reservation.length];
		for(int i = 0; i<this.reservation.length; i++) {
			a[i]= this.reservation[i];
		}
		return a;
	}
	public int add(Reservation res) {
		Reservation [] newres = new Reservation[this.reservation.length+1];
		for (int i = 0; i<this.reservation.length; i++) {
			newres[i]= this.reservation[i];
		}
		newres[this.reservation.length]= res;
		reservation = newres;
		return this.reservation.length;
	}
	public boolean remove (Reservation res) {
		boolean removesuccess= false;
		int index = 0;
		int indexnew = 0;
		for (int i = 0; i<this.reservation.length;i++) {
			if (res.equals(this.reservation[i])){
				removesuccess = true;
			}
		}
		if (removesuccess) {
			int occurrence = 0;
			Reservation[] copy = new Reservation[reservation.length-1];
			
				while (index<this.reservation.length-1) {
					if (occurrence == 0) {
						if (res.equals(reservation[index])) {
							index++;
							copy[indexnew]= reservation[index];
							occurrence = 1;
							indexnew++;
							index++;
						}else {
							copy[indexnew]= reservation[index];
							index++;
							indexnew++;
						}
					}else {
						copy[indexnew]= reservation[index];
						index++;
						indexnew++;
					}
				}
				reservation = copy;
			}
		
		return removesuccess;
	}
	public void clear() {
		this.reservation= new Reservation[0];
	}
	public int getNumOfReservations () {
		return this.reservation.length;
	}
	public int getTotalCost () {
		int result= 0;
		
		for (int i = 0; i<this.reservation.length; i++) {
			int price = this.reservation[i].getCost();
			result+=price;
		}
		return result;
	}
	
	
	

}
