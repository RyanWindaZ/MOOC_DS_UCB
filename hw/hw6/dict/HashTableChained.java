/* HashTableChained.java */

package dict;
import list.*;

/**
 *  HashTableChained implements a Dictionary as a hash table with chaining.
 *  All objects used as keys must have a valid hashCode() method, which is
 *  used to determine which bucket of the hash table an entry is stored in.
 *  Each object's hashCode() is presumed to return an int between
 *  Integer.MIN_VALUE and Integer.MAX_VALUE.  The HashTableChained class
 *  implements only the compression function, which maps the hash code to
 *  a bucket in the table's range.
 *
 *  DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

  /**
   *  Place any data fields here.
   **/
  private List[] buckets;
  private int bucketsSize;

  /**
   *  Find prime number around sizeEstimate
   **/
  public int findPrime(int sizeEstimate) {
    boolean isPrime = false;
    int num = sizeEstimate;
    
    while (!isPrime) {
        
        isPrime = true;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                isPrime = false;
                num++;
                break;
            }
        }
    }
    return num;
  }
  


  /** 
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
    int primeNum = findPrime(sizeEstimate);
    buckets = new DList[primeNum];
  }

  /** 
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    this(100);
  }

  /**
   *  Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
   *  to a value in the range 0...(size of hash table) - 1.
   *
   *  This function should have package protection (so we can test it), and
   *  should be used by insert, find, and remove.
   **/

  int compFunction(int code) {
    // Replace the following line with your solution.
    int result = Math.abs(code)%bucketLength();

    return result;
  }

  /** 
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return bucketsSize;
  }

  /** 
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return bucketsSize == 0;
  }

  /**
   *  Create a new Entry object referencing the input key and associated value,
   *  and insert the entry into the dictionary.  Return a reference to the new
   *  entry.  Multiple entries with the same key (or even the same key and
   *  value) can coexist in the dictionary.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the key by which the entry can be retrieved.
   *  @param value an arbitrary object.
   *  @return an entry containing the key and value.
   **/

  public Entry insert(Object key, Object value) {
    // Replace the following line with your solution.
    if (key != null) {
        Entry newEntry = new Entry();
        newEntry.key = key;
        newEntry.value = value;
        
        int compCode = compFunction(key.hashCode());
        buckets[compCode].insertBack(newEntry);
        
        bucketsSize++;
        return newEntry;
    }
    else
        return null;
  }

  /** 
   *  Search for an entry with the specified key.  If such an entry is found,
   *  return it; otherwise return null.  If several entries have the specified
   *  key, choose one arbitrarily and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   **/

  public Entry find(Object key) {
    // Replace the following line with your solution.
    if (key != null) {        
        int compCode = compFunction(key.hashCode());
        ListNode curNode = buckets[compCode].front(); 
                
        try {
            
            while(curNode.isValidNode()) {
                if (((Entry)curNode.item()).key().equals(key)) {
                    return (Entry)curNode.item();
                }
                curNode = curNode.next();
            }
            return null;
            
        } catch(InvalidNodeException ex) {
            System.err.println(ex);
            return null;
        }
        
    }
    else {
        return null;
    }
  }

  /** 
   *  Remove an entry with the specified key.  If such an entry is found,
   *  remove it from the table and return it; otherwise return null.
   *  If several entries have the specified key, choose one arbitrarily, then
   *  remove and return it.
   *
   *  This method should run in O(1) time if the number of collisions is small.
   *
   *  @param key the search key.
   *  @return an entry containing the key and an associated value, or null if
   *          no entry contains the specified key.
   */

  public Entry remove(Object key) {
    // Replace the following line with your solution.
    int compCode = compFunction(key.hashCode());
    ListNode curNode = buckets[compCode].front();
    
    try {
        
        while(curNode.isValidNode()) {
            if (((Entry)curNode.item()).key().equals(key)) {
                Entry e = (Entry)curNode.item();
                curNode.remove();
                bucketsSize--;
                return e;
            }
            curNode = curNode.next();
        }
        return null;

    } catch(InvalidNodeException ex) {
        System.err.println(ex);
        return null;
    }
        
    
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    // Your solution here.
    for(int i = 0; i < bucketLength(); i++) {
        buckets[i] = new DList();
    }
    bucketsSize = 0;
  }

  /**
   * @return expected number of collisions
   */
  public double expeCollisionNum() {
    int n = bucketsSize;
    int N = bucketLength();

    // not 1/N, it will keep only integer part
    return n - N + N * Math.pow((1 - 1.0/N), n);
  }

  /**
   * @return exact number of collisions
   */
  public int exacCollisionNum() {
    int num = 0;
    for (int i = 0; i < bucketLength(); i++) {
      if (buckets[i].length() > 1) num++;
    }    
    return num;      
  }

  public void printHistogram() {
    for (int i = 0; i < bucketLength(); i++) {
      System.out.print("[" + buckets[i].length() + "]");
    }
  }

  public int bucketLength() {
    return buckets.length;
  }

  public static void main (String[] args) {
    HashTableChained h = new HashTableChained();
    System.out.println(h.findPrime(2));
    System.out.println(h.findPrime(3));
    System.out.println(h.findPrime(5));
    System.out.println(h.findPrime(9));
    System.out.println(h.findPrime(100));
  }
}
