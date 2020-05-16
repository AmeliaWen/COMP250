
public class Room {
	//fields
	private String type;
	private int price;
	private boolean isAvailable;
	//constructor1
	
	public Room(String type) {
		if(type.equalsIgnoreCase("double")) {
			this.type = type.toLowerCase();
			this.price = 9000;
			this.isAvailable = true;
		}else if(type.equalsIgnoreCase("queen")){
			this.type = type.toLowerCase();
			this.price = 11000;
			this.isAvailable = true;
		}else if(type.equalsIgnoreCase("king")) {
			this.type = type.toLowerCase();
			this.price = 15000;
			this.isAvailable = true;
		}else {
			throw new IllegalArgumentException("No room is can be created");
		}
	}
	//constructor2
	public Room (Room a) {
		this.isAvailable= a.isAvailable;
		this.price= a.price;
		this.type= a.type;
		
	}
	//other methods
	public String getType() {
		return this.type;
	}
	public int getPrice() {
		return this.price;
	}
	public void changeAvailability() {
		this.isAvailable= !this.isAvailable;
	}
	public static Room findAvailableRoom (Room[] a, String str) {
		//Room [] b = new Room[a.length];
		for(int i = 0; i<a.length;i++) {
			//b[i]= a[i];
			if (a[i].getType().equalsIgnoreCase(str)&& a[i].isAvailable) {
				return a[i];
			}
		}
		return null;
	}
	public static boolean makeRoomAvailable (Room []a, String str) {
		for (int i = 0; i<a.length; i++) {
			if ( a[i].getType().equalsIgnoreCase(str)&& ! a[i].isAvailable) {
				a[i].isAvailable= true;
				return true;
			}
		}
		return false;
	}
}
