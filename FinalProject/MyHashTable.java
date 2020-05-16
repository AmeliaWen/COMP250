
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

//import MyHashTable.MyHashIterator;


public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        this.numBuckets= initialCapacity;
        this.numEntries=0;
        this.buckets= new ArrayList<LinkedList<HashPair<K, V>>>(initialCapacity);
        for (int i = 0;i<initialCapacity;i++) {
        	LinkedList<HashPair<K, V>> ini = new LinkedList<HashPair<K, V>>();
        	this.buckets.add(ini);
        }
        
        
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public boolean isEmpty() {
        return this.numEntries == 0;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
   
    private HashPair<K, V> getHashPair(K key){
    	if (buckets.isEmpty()) {
    		return null;
    	}
    	for (HashPair<K, V> cur : this.buckets.get(hashFunction(key))) {
    		if (cur.getKey().equals(key)) {
    			return cur;
    		}
    	}
    	return null;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
    	//numEntries++;
    	if (key == null) {
    		return null;
    	}
    	V oldvalue = get(key);
    	

    	if (oldvalue != null) {
    		HashPair<K, V> oldpair = getHashPair(key);
    		oldpair.setValue(value);
    	
    		return oldvalue;
    	//if (this.buckets.get(hashFunction(key)).get(0)== null) {
    		
    	}else {
    		numEntries++;
    		if ((double)(numEntries/numBuckets) > MAX_LOAD_FACTOR) {
        		rehash();
        	}
    		HashPair<K, V> newpair = new HashPair<K, V>(key, value);
    		this.buckets.get(hashFunction(key)).add(newpair);
    		
    		return value;
    	}
    	//}
    	
    	
    	
        
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
    	
        if (this.buckets.get(hashFunction(key)) != null &&(! this.buckets.isEmpty())) {
        	for (HashPair<K, V> kv: this.buckets.get(hashFunction(key))) {
        		if (kv.getKey().equals(key)) {
        			return kv.getValue();
        		}
        	}
        	
        	
        	//while(this.buckets.get(hashFunction(key)).g)
        }
    	return null;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
        if (getHashPair(key)!= null) {
        	numEntries--;
        	V value = getHashPair(key).getValue();
        	this.buckets.get(hashFunction(key)).remove(getHashPair(key));
        	return value;
        }
    	return null;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /** 
     * Method to double the size of the hashtable if load factor increases
     * beyond MAX_LOAD_FACTOR.
     * Made public for ease of testing.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    public void rehash() {
    	MyHashTable<K, V> newHashTable = new MyHashTable<K, V>(this.numBuckets*2);
    	
    	for(int i=0; i<this.numBuckets; i++) {
    		for(int j=0; j<this.buckets.get(i).size(); j++) {
    			HashPair<K, V> pair = this.buckets.get(i).get(j);
    			newHashTable.put(pair.getKey(), pair.getValue());
    		}
    	}
    	
    	this.numEntries = newHashTable.numEntries;
    	this.numBuckets = newHashTable.numBuckets;
    	this.buckets = newHashTable.buckets;
        //ADD YOUR CODE BELOW HERE
    	//this.numBuckets= 2*this.numBuckets;
    	//LinkedList<HashPair<K, V>>[] temp = new LinkedList<HashPair<K, V>>[this.numBuckets/2];
    	//ArrayList<LinkedList<HashPair<K, V>>> temp = new ArrayList<LinkedList<HashPair<K, V>>>(numBuckets);
    	//LinkedList<HashPair<K, V>> temp = new LinkedList<HashPair<K, V>>();
    	//for (LinkedList<HashPair<K, V>> pair: this.buckets) {
    	//	temp.add(pair);
    	//}
    	//this.buckets.clear();
    	//for (LinkedList<HashPair<K, V>> newpair : temp) {
    	//	this.buckets.add(newpair);
    	//}
    	//for (int i = 0;i<this.numBuckets;i++) {
    	//	temp.add(new LinkedList<HashPair<K, V>>());
    	//}
    	//for(HashPair<K, V> hp : this) {
        //	int pos = hashFunction(hp.getKey());
        //	LinkedList<HashPair<K, V>> bucket = temp.get(pos);
        	
        //	if(bucket.isEmpty()) {
        //		bucket.add(hp);
        	//If the bucket is not empty, we walk through its elements
        //	}else {
        //		for (HashPair<K, V> hashPair : bucket) {
        			//If the key already exist, set the new value
    	//			if(hashPair.getKey().equals(hp.getKey())) {
    	//				hashPair.setValue(hp.getValue());
    	//			}
    	//		}
        		//If the key is not exist, add the KV pair
        //		bucket.add(hp);
        //	}
       // }
        
        //this.buckets = temp;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     * Expected average runtime is O(m), where m is the number of buckets
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE
        ArrayList<K> list = new ArrayList<K>();
        for (LinkedList<HashPair<K, V>> pairs: this.buckets) {
        	if (pairs!= null && (! pairs.isEmpty())) {
        	for (int i = 0;i<pairs.size();i++) {
        		list.add(pairs.get(i).getKey());
        	}
        	}
        }
    	return list;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(m) where m is the number of buckets
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	//HashSet<V> set = new HashSet<V>();
    	ArrayList<V> list = new ArrayList<V>();
    	MyHashTable<V, Integer> intermediate = new MyHashTable<V, Integer>(this.numBuckets);
    	for (LinkedList<HashPair<K, V>> pairs: this.buckets) {
    		if (pairs != null &&(!pairs.isEmpty())) {
    			for (int i = 0;i<pairs.size();i++) {
    				intermediate.put(pairs.get(i).getValue(), 1);
    				//System.out.println(pairs.get(i).getValue());
    				//list.add(pairs.get(i).getValue());
    			}
        	//for (HashPair<K, V> pair : pairs) {
        	//	if (!list.contains(pair.getValue())) {
        	//	list.add(pair.getValue());
        	//	}
        	//}
    		}
        }
    	
    	
    	ArrayList<V> result = new ArrayList<V>();
    	result = intermediate.keys();
    	//System.out.println(result.size());
    	
    	return result;
    	
        //ADD CODE ABOVE HERE
    }
    
    
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2), where n is the number 
	 * of pairs in the map. 
	 */
    public static <K, V extends Comparable<V>> ArrayList<K> slowSort (MyHashTable<K, V> results) {
        ArrayList<K> sortedResults = new ArrayList<K>();
        for (HashPair<K, V> entry : results) {
			V element = entry.getValue();
			K toAdd = entry.getKey();
			int i = sortedResults.size() - 1;
			V toCompare = null;
        	while (i >= 0) {
        		toCompare = results.get(sortedResults.get(i));
        		if (element.compareTo(toCompare) <= 0 )
        			break;
        		i--;
        	}
        	sortedResults.add(i+1, toAdd);
        }
        return sortedResults;
    }
    
    private static <K, V extends Comparable<V>> int partition(ArrayList<V> arr, int low, int high) {
    	
        int j,  i = low + 1;
        int x =  low;
        
        
        for (j = low + 1; j <= high; j++) {
             if (arr.get(j).compareTo(arr.get(low))<=0) {
                i++;
            }
        }
        
        return i - 1;
    }
    // Function to implement quick sort
    private static <K, V extends Comparable<V>>void quickSort(ArrayList<V> array,int low,int high){
        if(low<high){
            int mid = partition(array,low,high);
            quickSort(array,low,mid-1);
            quickSort(array,mid+1,high);
        }
    }
	/**
	 * This method takes as input an object of type MyHashTable with values that 
	 * are Comparable. It returns an ArrayList containing all the keys from the map, 
	 * ordered in descending order based on the values they mapped to.
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
    private static <K, V extends Comparable<V>> void merge(int low, int mid, int high, ArrayList<HashPair<K, V>> values
			) {
		int left = low;
		int right = mid + 1;
		ArrayList<HashPair<K, V>> aux = new ArrayList<HashPair<K, V>>();
		for(int i = low; i <= high; i++){
			aux.set(i, values.get(i));
		}
	
		while(left <= mid && right <= high){
			values.set(low++, aux.get(left).getValue().compareTo(aux.get(right).getValue()) > 0 ? aux.get(left++) : aux.get(right++));
		}
	
		while(left <= mid){
			values.set(low++, aux.get(left++));
		}
	}
	
    private static <K, V extends Comparable<V>>void merge (ArrayList<HashPair<K,V>>  arr, int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
        ArrayList<HashPair<K,V>> L = new ArrayList<HashPair<K,V>>();
        ArrayList<HashPair<K,V>> R = new ArrayList<HashPair<K,V>>();
  
        /* Create temp arrays */
        //MyHashTable<K, V> L[] = new MyHashTable<K, V> [n1]; 
        //int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L.add(i, arr.get(l+i));
        for (int j=0; j<n2; ++j) 
           
        	R.add(j, arr.get(m+1+j));
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L.get(i).getValue().compareTo(R.get(j).getValue())<0) 
            { 
                //arr[k] = L[i]; 
                arr.add(k, L.get(i));
                i++; 
            } 
            else
            { 
                //arr[k] = R[j]; 
                arr.add(k, R.get(j));
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            //arr[k] = L[i]; 
            arr.set(k, L.get(i));
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            //arr[k] = R[j];
            arr.set(k, R.get(j));
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    private static <K, V extends Comparable<V>> void sort(ArrayList<HashPair<K,V>> arr, int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            sort(arr, l, m); 
            sort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(l, m, r, arr);
            //merge(arr, l, m, r); 
        } 
    } 
   private static <K, V extends Comparable<V>> void place(HashPair<K, V>pair, MyHashTable<K, V> results, ArrayList<K> sortedResults) {
	   int left = 0; 
   		int right = sortedResults.size()-1;
   		int mid = 0;
   		while(left<=right) {
   		
   		mid = (left+right)/2;
   		if (pair.getValue().compareTo(results.get(sortedResults.get(mid)))>0) {
   			right = mid-1;
   		}else {
   			
   			left = mid+1;
   		}
   	}
   		sortedResults.add(right+1, pair.getKey());
   		
   }
    public static <K, V extends Comparable<V>> ArrayList<K> fastSort(MyHashTable<K, V> results) {
        //ADD CODE BELOW HERE
    	//ArrayList<HashPair<K,V>> sorted1 = new ArrayList<>();
    	//for (HashPair<K, V> entry : results) {
    	//	sorted1.add(entry);
    	//}
    	//sort(sorted1, 0, results.values().size()-1);
    	//ArrayList<K> keys = new ArrayList<K>();
    	//for (int i=0;i<sorted1.size();i++) {
    //		keys.add(sorted1.get(i).getKey());
    //	}
    	
        //Iterate through all the existing entries in the HashTable
        //for (HashPair<K, V> entry : results) {
		//	V element = entry.getValue();
		//	K toAdd = entry.getKey();
		//	//first compare with the middle element
		//	int mid = 0;
		//	int left = 0;
		//	int right = sortedResults.size()-1;
		//	V toCompare = null;
        //	while (left<=right) {
        //		mid = (left+right)/2;
        //		//you need to get the value from the hashtable every time you want to compare the value
        //		toCompare = results.get(sortedResults.get(mid));
        ///		//System.out.println("Left:"+left+" Mid:"+mid+" Right:"+right);
        		//When the element is less than the mid, then go right
        	//	if(element.compareTo(toCompare)<0) {
        	//		left = mid+1;
        	//	}else {
        	//		right = mid-1;
        	//	}
        	//}//
        //	sortedResults.add(right+1, toAdd);
        //}
        //return sortedResults;
    	
    	//ArrayList<V> sorted = results.values();
    	int n = results.numEntries;
    	ArrayList<K> sortedResults = new ArrayList<K>(n);
    	//sort(sorted, 0, results.values().size()-1);
    	//ArrayList<K> sortedResults = new ArrayList<K>();
    	//quickSort(sorted, 0, results.values().size()-1);
    	
    	for (HashPair<K, V> entry: results) {
    		place(entry, results, sortedResults);
    	//	
    	//int left = 0; 
    	//int right = sortedResults.size()-1;
    	//int mid = 0;
    	//while(left<=right) {
    		
    	//	mid = (left+right)/2;
    	//	if (entry.getValue().compareTo(results.get(sortedResults.get(mid)))>0) {
    	//		right = mid-1;
    	//	}else {
    			
    	//		left = mid+1;
    	//	}
    	//}
    	//System.out.println(sorted.size());
    	
    		//sortedResults.add(sorted.indexOf(entry.getValue()), entry.getKey());
    		//System.out.println(sortedResults.size()+"wtf");
    	}
    	return sortedResults;
		
        //ADD CODE ABOVE HERE
    }

    
    
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }   
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        //ADD YOUR CODE BELOW HERE
    	ArrayList<HashPair<K, V>> entries = new ArrayList<HashPair<K, V>>();
    	//HashPair<K, V> cur = buckets.get(0).getFirst();
    	int index = 0;
        //ADD YOUR CODE ABOVE HERE
    	
    	/**
    	 * Expected average runtime is O(m) where m is the number of buckets
    	 */
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
        	//entries = new LinkedList<HashPair<K, V>>();
        	for (LinkedList<HashPair<K, V>> pairs : buckets) {
        		//HashPair<K, V> cur = pairs.getFirst();
        		if (pairs!= null &&(!pairs.isEmpty())) {
        		for (int i = 0;i<pairs.size();i++) {
        			entries.add(pairs.get(i));
        		}
        		}
        	}
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
        	return index<entries.size();
        	//return !entries.isEmpty();
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        /**
         * Expected average runtime is O(1)
         */
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	HashPair<K, V> pair = entries.get(index);
        	index++;
        	return pair;
        	//return entries.removeFirst();
        	
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
