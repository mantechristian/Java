/* HashTableChained.java */

//package dict;
import java.util.*;
import java.io.*;

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
  private List[] table;
  private int size;
  private static final int loadFactor = 1;
  private static final int length = 101;


  /**
   *  Construct a new empty hash table intended to hold roughly sizeEstimate
   *  entries.  (The precise number of buckets is up to you, but we recommend
   *  you use a prime number, and shoot for a load factor between 0.5 and 1.)
   **/

  public HashTableChained(int sizeEstimate) {
    // Your solution here.
   table = new List[sizeEstimate];
    for(int i = 0; i < sizeEstimate; i++)
      table[i] = new LinkedList();
    size = 0;
  }

  /**
   *  Construct a new empty hash table with a default size.  Say, a prime in
   *  the neighborhood of 100.
   **/

  public HashTableChained() {
    // Your solution here.
    table = new List[length];
    for(int i = 0; i < length; i++)
      table[i] = new LinkedList();
    size = 0;
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
    return Math.abs(((13 * code + 1071) % 16978013 ) % table.length);
  }

  /**
   *  Returns the number of entries stored in the dictionary.  Entries with
   *  the same key (or even the same key and value) each still count as
   *  a separate entry.
   *  @return number of entries in the dictionary.
   **/

  public int size() {
    // Replace the following line with your solution.
    return size;
  }

  /**
   *  Tests if the dictionary is empty.
   *
   *  @return true if the dictionary has no entries; false otherwise.
   **/

  public boolean isEmpty() {
    // Replace the following line with your solution.
    return size == 0;
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
    Entry myEntry = new Entry(key,value);
    int bucket = compFunction(key.hashCode());
    table[bucket].add(myEntry);
    size++;
    return myEntry;
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
    int bucket = compFunction(key.hashCode());
    Iterator i = table[bucket].listIterator(0);
    while(i.hasNext()){
      Entry e = (Entry)i.next();
      if(e.key.equals(key))
        return e;
    }
    return null;
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
    int bucket = compFunction(key.hashCode());
    Iterator i = table[bucket].listIterator(0);
    int position = 0;
    while(i.hasNext())
    {
      Entry e = (Entry)i.next();
      if(e.key.equals(key)){
        table[bucket].remove(position);
        size--;
        return e;
      }
      position++;
    }
      return null;
  }

  /**
   *  Remove all entries from the dictionary.
   */
  public void makeEmpty() {
    for(int i = 0; i < table.length; i++){
      Iterator iterator = table[i].listIterator(0);
      for(int j = 0; iterator.hasNext(); j++)
      {
        table[i].remove(j);
      }
    }
  }

  public  void avesize(){
    int sum = 0;
    for (int i=0;i<table.length;i++) {
      sum = sum + table[i].size();
    }
    int ave = sum/table.length;
    System.out.println("Ave length:"+ave);
  }
  public int longest(){
    int max = 0;
    for (int i=0;i<table.length;i++) {
      if(max<table[i].size()){
        max = table[i].size();
      }
    }
    return max;
  }

  public int smallest(){
    int min = 0;
    for (int i=0;i<table.length;i++) {
      if(min> table[i].size()){
        min = table[i].size();
      }
    }
    return min;
  }

  public static ArrayList loadList(String filename){
    Scanner in;
    ArrayList a = new ArrayList();
    try{
       in = new Scanner(new FileReader(filename));
      while(in.hasNext()){
        a.add(in.next());
      }
    }catch(IOException e){
      System.out.println(e.getMessage());
    }
    return a;
  }

  public static void main(String[] args){
      HashTableChained d = new HashTableChained();
      System.out.println("isEmpty:"+d.isEmpty()+" should be true.");
      Entry e = new Entry("a","defn");
      d.insert(e.key, e.value);
      Entry e2 = d.find(e.key);
      System.out.println("found :"+e2.key+" should be a");
      d.remove(e.key);
      System.out.println("found :"+d.find(e.key)+" should be null");
      Entry e3 = new Entry("a", "defn");
      d.insert(e3.key, e3.value);
      Entry e4 = d.find(e3.key);
      System.out.println("found :"+e4.key+" should be a");
      System.out.println(d.size());

      HashTableChained h = new HashTableChained(10000);
      ArrayList a = HashTableChained.loadList("C:/Users/Christian/Documents/CMSC/CMSC123/dict/wordlist.txt");
      ListIterator it = a.listIterator(0);
      while(it.hasNext()){
        h.insert(it.next(), "testdef");
      }
      System.out.println("Size: " + h.size());
      System.out.println(h.find("abt"));

      h.avesize();
      System.out.println("Longest: " + h.longest());
      System.out.println("Smallest: "+ h.smallest());
  }

}
