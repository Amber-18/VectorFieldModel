package objects;

public class LinkedList<T> {
	
	private T object;
	private LinkedList<T> next_link;
	
	public LinkedList(){
		object = null;
	}
	
	public LinkedList(T obj) {
		object = obj;
	}
	
	public void add(T obj) {
		if(object == null) {
			object = obj;
		} else if(next_link == null){
			next_link = new LinkedList<T>(obj);
		} else {
			next_link.add(obj);
		}
	}
	
	public int size() {
		if(next_link == null) {
			if(object == null) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1 + next_link.size();
		}
	}
	
	public T get(int i) {
		if(i <= 0) {
			return object;
		} else {
			return next_link.get(i-1);
		}
	}
	
	public void remove(int i) {
		if(i <= 0) {
			if(next_link == null) {
				object = null;
				return;
			} else {
				object = next_link.get(0);
				LinkedList<T> temp_link = next_link.getNextLink();
				next_link.setToNull();
				next_link = temp_link;
			}
			
		} else {
			next_link.remove(i - 1);
		}
	}
	
	protected LinkedList<T> getNextLink() {
		return next_link;
	}
	
	protected void setNextLink(LinkedList<T> link) {
		next_link = link;
	}
	
	protected void setToNull() {
		next_link = null;
		object = null;
	}
	
	public void set(int i, T obj) {
		if(i <= 0) {
			object = obj;
		} else {
			if(next_link != null) {
				next_link.set(i - 1,  obj);
			} else {
				next_link = new LinkedList<T>(obj);
			}
			
		}
	}
	
	public void add(int i, T obj) {
		if(i == 0) {
			LinkedList<T> new_link = new LinkedList<T>(object);
			new_link.setNextLink(next_link);
			next_link = new_link;
			object = obj;
		} else {
			next_link.add(i - 1, obj);
		}
	}
	
	public String toString() {
		return "{" + toString(size());
	}
	
	protected String toString(int i) {
		if(i == 1) {
			return object.toString() + "}";
		} else {
			return object.toString() + ", " + next_link.toString(i - 1);
		}
	}
	
	
}
