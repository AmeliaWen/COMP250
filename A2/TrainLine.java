import java.util.Arrays;
import java.util.Random;

public class TrainLine {

	private TrainStation leftTerminus;
	private TrainStation rightTerminus;
	private String lineName;
	private boolean goingRight;
	public TrainStation[] lineMap;
	public static Random rand;

	public TrainLine(TrainStation leftTerminus, TrainStation rightTerminus, String name, boolean goingRight) {
		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		this.lineMap = this.getLineArray();
	}

	public TrainLine(TrainStation[] stationList, String name, boolean goingRight)
	/*
	 * Constructor for TrainStation input: stationList - An array of TrainStation
	 * containing the stations to be placed in the line name - Name of the line
	 * goingRight - boolean indicating the direction of travel
	 */
	{
		TrainStation leftT = stationList[0];
		TrainStation rightT = stationList[stationList.length - 1];

		stationList[0].setRight(stationList[stationList.length - 1]);
		stationList[stationList.length - 1].setLeft(stationList[0]);

		this.leftTerminus = stationList[0];
		this.rightTerminus = stationList[stationList.length - 1];
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;

		for (int i = 1; i < stationList.length - 1; i++) {
			this.addStation(stationList[i]);
		}

		this.lineMap = this.getLineArray();
	}

	public TrainLine(String[] stationNames, String name,
			boolean goingRight) {/*
									 * Constructor for TrainStation. input: stationNames - An array of String
									 * containing the name of the stations to be placed in the line name - Name of
									 * the line goingRight - boolean indicating the direction of travel
									 */
		TrainStation leftTerminus = new TrainStation(stationNames[0]);
		TrainStation rightTerminus = new TrainStation(stationNames[stationNames.length - 1]);

		leftTerminus.setRight(rightTerminus);
		rightTerminus.setLeft(leftTerminus);

		this.leftTerminus = leftTerminus;
		this.rightTerminus = rightTerminus;
		this.leftTerminus.setLeftTerminal();
		this.rightTerminus.setRightTerminal();
		this.leftTerminus.setTrainLine(this);
		this.rightTerminus.setTrainLine(this);
		this.lineName = name;
		this.goingRight = goingRight;
		for (int i = 1; i < stationNames.length - 1; i++) {
			this.addStation(new TrainStation(stationNames[i]));
		}

		this.lineMap = this.getLineArray();

	}

	// adds a station at the last position before the right terminus
	public void addStation(TrainStation stationToAdd) {
		TrainStation rTer = this.rightTerminus;
		TrainStation beforeTer = rTer.getLeft();
		rTer.setLeft(stationToAdd);
		stationToAdd.setRight(rTer);
		beforeTer.setRight(stationToAdd);
		stationToAdd.setLeft(beforeTer);

		stationToAdd.setTrainLine(this);

		this.lineMap = this.getLineArray();
	}

	public String getName() {
		return this.lineName;
	}

	public int getSize() {

		// YOUR CODE GOES HERE
		return this.lineMap.length; // change this!
	}

	public void reverseDirection() {
		this.goingRight = !this.goingRight;
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation travelOneStation(TrainStation current, TrainStation previous) {

		TrainStation a=new TrainStation("null");
		TrainStation tar;
		
		for(int i=0;i<this.lineMap.length;i++) {
			if(this.lineMap[i].equals(current)) {
				a=this.lineMap[i];				
			}				
		}
		if(a.getName()=="null") {
			throw new StationNotFoundException(current.getName());			
		}
		else {
			if((a.hasConnection)&&(!a.getTransferStation().equals(previous))) {
				tar=a.getTransferStation();
			}
			else tar=this.getNext(a);
		}
			
		// YOUR CODE GOES HERE
		return tar; // change this!
	}

	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation getNext(TrainStation station) {
		TrainStation tar;
		TrainStation a= this.leftTerminus;
		
		while(station.getName()!=a.getName()) {
			a=a.getRight();// YOUR CODE GOES HERE
		}
		if (a.equals(null)) {
			throw new StationNotFoundException(station.getName());
		}
		else if(a.equals(this.rightTerminus)) {
			tar=a.getLeft();
		}
		else if(a.equals(this.leftTerminus)) {
			tar=a.getRight();
		}
		else {
			if(this.goingRight) {
			tar=a.getRight();}
			else tar=a.getLeft();
		}
		return tar; // change this!
	
		
	}
	// You can modify the header to this method to handle an exception. You cannot make any other change to the header.
	public TrainStation findStation(String name) {

		for (int i=0;i<this.lineMap.length;i++) {
			if (lineMap[i].getName()==name) {
				return lineMap[i];// YOUR CODE GOES HERE
			}
			
		}
		throw new StationNotFoundException(name);
		
	}

	public void sortLine() {
		TrainStation[] a=this.getLineArray();
		
		for(int i=0;i<a.length-1;i++) {
			int index=i;
			for(int j=index+1;j<a.length;j++) {
				if (a[j].getName().compareTo(a[i].getName())<0) {
					index=j;	
			TrainStation iLeft =a[i].getLeft();
			TrainStation iRight =a[i].getRight();
			TrainStation nLeft =a[index].getLeft();
			TrainStation nRight =a[index].getRight();
			TrainStation c =a[index];
			a[index]=a[i];
			a[i]=c;
			a[index].setLeft(iLeft);
			a[index].setRight(iRight);
			a[i].setLeft(nLeft);
			a[i].setRight(nRight);
		
		}}}
		
		this.lineMap=a;
		// YOUR CODE GOES HERE
	}
	
	

	public TrainStation[] getLineArray() {
		TrainStation tmp=this.leftTerminus;
		int num=1;
		while(tmp.getRight()!=null) {
			num++;
			tmp=tmp.getRight();
		}
		tmp=this.leftTerminus;
		TrainStation[] tar=new TrainStation[num];
		tar[0]=this.leftTerminus;
		//tar[tar.length-1]=this.rightTerminus;
		
		for(int i=1;i<tar.length;i++) {
			tar[i]=tmp.getRight();
			tmp=tmp.getRight();
		}
		// YOUR CODE GOES HERE
		return tar; // change this
	}

	private TrainStation[] shuffleArray(TrainStation[] array) {
		Random rand = new Random();

		for (int i = 0; i < array.length; i++) {
			int randomIndexToSwap = rand.nextInt(array.length);
			TrainStation temp = array[randomIndexToSwap];
			array[randomIndexToSwap] = array[i];
			array[i] = temp;
		}
		this.lineMap = array;
		return array;
	}

	public void shuffleLine() {

		// you are given a shuffled array of trainStations to start with
		TrainStation[] lineArray = this.getLineArray();
		TrainStation[] shuffledArray = shuffleArray(lineArray);
		TrainStation tmp=shuffledArray[0];
		if(lineMap.length>2) {
			this.leftTerminus=lineMap[0];
			lineMap[0].setLeftTerminal();
			lineMap[0].setLeft(null);
			lineMap[0].setRight(lineMap[1]);
			this.rightTerminus=lineMap[lineMap.length-1];
			lineMap[lineMap.length-1].setLeft(lineMap[lineMap.length-2]);
			lineMap[lineMap.length-1].setRight(null);
			lineMap[lineMap.length-1].setRightTerminal();
			for (int i=1;i<lineMap.length-1;i++) {
				lineMap[i].setLeft(lineMap[i-1]);
				lineMap[i].setRight(lineMap[i+1]);
		}
				}
		else {
			this.leftTerminus=lineMap[0];
			lineMap[0].setLeft(null);
			lineMap[0].setRight(lineMap[1]);
			lineMap[0].setLeftTerminal();
			this.rightTerminus=lineMap[1];
			lineMap[1].setRightTerminal();
			lineMap[1].setLeft(lineMap[0]);
			lineMap[1].setRight(null);
			
		}

		// YOUR CODE GOES HERE

	}

	public String toString() {
		TrainStation[] lineArr = this.getLineArray();
		String[] nameArr = new String[lineArr.length];
		for (int i = 0; i < lineArr.length; i++) {
			nameArr[i] = this.lineMap[i].getName();
		}
		return Arrays.deepToString(nameArr);
	}

	public boolean equals(TrainLine line2) {

		// check for equality of each station
		TrainStation current = this.leftTerminus;
		TrainStation curr2 = line2.leftTerminus;

		try {
			while (current != null) {
				if (!current.equals(curr2))
					return false;
				else {
					current = current.getRight();
					curr2 = curr2.getRight();
				}
			}

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public TrainStation getLeftTerminus() {
		return this.leftTerminus;
	}

	public TrainStation getRightTerminus() {
		return this.rightTerminus;
	}
}

//Exception for when searching a line for a station and not finding any station of the right name.
class StationNotFoundException extends RuntimeException {
	String name;

	public StationNotFoundException(String n) {
		name = n;
	}

	public String toString() {
		return "StationNotFoundException[" + name + "]";
	}
}
