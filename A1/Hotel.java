
public class Hotel {
	//attributes
	private String name;
	private Room[] rooms;
	//constructor
	public Hotel (String name, Room []rooms) {
		this.name= name;
		Room [] copy = new Room [rooms.length];
		for (int i = 0; i< rooms.length;i++) {
			copy[i]=  new Room(rooms[i]);
		}
		this.rooms= copy;
		
	}
	public int reserveRoom (String str) {
		Room a = Room.findAvailableRoom(this.rooms, str);
			if (a != null) {
				a.changeAvailability();
				return a.getPrice();
			
			}else {
		
		throw new IllegalArgumentException("not able to reserve a room");
	}
	}
	
	public boolean cancelRoom (String str) {
		
			return Room.makeRoomAvailable(this.rooms, str);
	}

}
