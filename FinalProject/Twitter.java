

import java.util.ArrayList;

public class Twitter {
	
	//ADD YOUR CODE BELOW HERE
	private MyHashTable<String, Tweet> authors;
	private MyHashTable<String, ArrayList<Tweet>> dates;
	//private MyHashTable<Tweet, ArrayList<String> > newor;
	private MyHashTable<String, Integer> ocurrance;
	//private ArrayList<Tweet> tweets;
	private ArrayList<String> stops;
	//private ArrayList<String> newwords;
	//private int numOfEntries;
	//private int numOfBuckets;
	//ADD CODE ABOVE HERE 
	
	// O(n+m) where n is the number of tweets, and m the number of stopWords
	public Twitter(ArrayList<Tweet> tweets, ArrayList<String> stopWords) {
		//ADD YOUR CODE BELOW HERE
		this.authors= new MyHashTable<String, Tweet>(tweets.size());
		this.dates= new MyHashTable<String, ArrayList<Tweet>>(tweets.size());
		this.ocurrance= new MyHashTable<String, Integer>(tweets.size());
		this.stops= stopWords;
		for (Tweet tweet: tweets) {
			addTweet(tweet);
		}
		//this.numOfEntries= tweets.size();
		//this.numOfBuckets= stopWords.size();
		//this.tweets= tweets;
		
		
		
		//ADD CODE ABOVE HERE 
	}
	
	
    /**
     * Add Tweet t to this Twitter
     * O(1)
     */
	public void addTweet(Tweet t) {
		//ADD CODE BELOW HERE
		Tweet tw = this.authors.get(t.getAuthor());
		if (tw == null) {
			this.authors.put(t.getAuthor(), t);
		}else {
			if (tw.compareTo(t)<0) {
				this.authors.put(t.getAuthor(), t);
			}
		}
		ArrayList<Tweet> tweetdates = dates.get(t.getDateAndTime().split(" ")[0]);
		if (tweetdates == null) {
			tweetdates = new ArrayList<Tweet>();
			//System.out.println("wrong");
			//tweetdates.add(newT);
			this.dates.put(t.getDateAndTime().split(" ")[0], tweetdates);
		}
		tweetdates.add(t);
		//Tweet newT = new Tweet(t.getAuthor(), t.getDateAndTime(), t.getMessage());
		ArrayList<String> update = new ArrayList<String>();
		ArrayList<String> words = getWords(t.getMessage());
		
		
		for (String word: words) {
			int freq=1;
			if (update.contains(word)) {
				freq++;
			}
			if (this.stops.contains(word.toLowerCase())) {
				freq++;
			}
			if(freq==1) {
				Integer count = ocurrance.get(word);
				if (count == null) {
					ocurrance.put(word, 1);
				}else {
					ocurrance.put(word, count+1);
				}
			
				
			}
			update.add(word);
		}
		

		//ADD CODE ABOVE HERE 
	}
	

    /**
     * Search this Twitter for the latest Tweet of a given author.
     * If there are no tweets from the given author, then the 
     * method returns null. 
     * O(1)  
     */
    public Tweet latestTweetByAuthor(String author) {
        //ADD CODE BELOW HERE
    	return this.authors.get(author);

    	//return null;
    	
        //ADD CODE ABOVE HERE 
    }


    /**
     * Search this Twitter for Tweets by `date' and return an 
     * ArrayList of all such Tweets. If there are no tweets on 
     * the given date, then the method returns null.
     * O(1)
     */
    public ArrayList<Tweet> tweetsByDate(String date) {
        //ADD CODE BELOW HERE
    	return this.dates.get(date);
    	//return null;
    	
        //ADD CODE ABOVE HERE
    }
    
	/**
	 * Returns an ArrayList of words (that are not stop words!) that
	 * appear in the tweets. The words should be ordered from most 
	 * frequent to least frequent by counting in how many tweet messages
	 * the words appear. Note that if a word appears more than once
	 * in the same tweet, it should be counted only once. 
	 */
    public ArrayList<String> trendingTopics() {
        //ADD CODE BELOW HERE
    	//ArrayList<String> newlist = new ArrayList<String>();
    	//MyHashTable<String, Integer> ocurrance = new MyHashTable<String, Integer>(numOfBuckets);
    	//for (ArrayList<String> line : newor.values()) {
    	//	for (String key: line) {
    	//	Integer occur = ocurrance.get(key);
    	//	if (occur == null) {
    	//		occur = 1;
    	//		ocurrance.put(key, occur);
    	//	}else {
    	//		ocurrance.put(key, occur++);
    	//	}
    	//	}
    	//}
    	//for (int i = ocurrance.size();i>0;i--) {
    		
    	//}
    	return MyHashTable.fastSort(ocurrance);
    	
        //ADD CODE ABOVE HERE    	
    }
    
    
    
    /**
     * An helper method you can use to obtain an ArrayList of words from a 
     * String, separating them based on apostrophes and space characters. 
     * All character that are not letters from the English alphabet are ignored. 
     */
    private static ArrayList<String> getWords(String msg) {
    	msg = msg.replace('\'', ' ');
    	String[] words = msg.split(" ");
    	ArrayList<String> wordsList = new ArrayList<String>(words.length);
    	for (int i=0; i<words.length; i++) {
    		String w = "";
    		for (int j=0; j< words[i].length(); j++) {
    			char c = words[i].charAt(j);
    			if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'))
    				w += c;
    			
    		}
    		wordsList.add(w);
    	}
    	return wordsList;
    }

    

}
