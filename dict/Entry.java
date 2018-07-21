/* Entry.java */

//package dict;

/**
 *  A class for dictionary entries.
 *
 *  DO NOT CHANGE THIS FILE.  It is part of the interface of the
 *  Dictionary ADT.
 **/

public class Entry {

  protected Object key;
  protected Object value;

  Entry(Object key, Object value){
  	this.key = key;
  	this.value = value;
  }

  public Object key() {
    return key;
  }

  public Object value() {
    return value;
  }
  public boolean equals(Object o){
  	if(!(o instanceof Entry)){
  		return false;
  	}
  	Entry entry = (Entry)o;
  	if(this.key.equals(entry.key))
  		return true;
  	return false;
  }
  public String toString(){
  	return key+":"+value;
  }
  public static void main(String[] args){
  		// Entry a = new Entry();
  		// a.key = "a";
  		// a.value = "first";

  		// Entry b = new Entry();
  		// b.key = "b";
  		// a.value = "second";

  		// System.out.println(a.equals(b));
  }

}
