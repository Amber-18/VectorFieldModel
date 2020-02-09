package tests;

import objects.LinkedList;

public class LinkedListTest {
	
	public static void main(String[] args) {
		
		try{
			testEmptyLL();
		} catch(NullPointerException e) {
			//
		}
		
		try{
			testSingleElementLL();
		} catch(NullPointerException e) {
			//
		}
		
		testMultiElementLL();
		
	}
	
	public static void testEmptyLL() {
		LinkedList<String> str = new LinkedList<String>();
		print("Empty Tests");
		print("Size: " + str.size());
		print("First element: " + str.get(0));
		print("Neg element: " + str.get(-1));
		print("toString " + str.toString());
		print("Remove element 0: ");
		str.remove(0);
		print("No exceptions thrown, successful!");
	}
	
	public static void testSingleElementLL() {
		LinkedList<String> str = new LinkedList<String>();
		str.add(new String("test_string"));
		print("One Element Tests");
		print("Size: " + str.size());
		print("First element: " + str.get(0));
		print("Neg element: " + str.get(-1));
		print("toString " + str.toString());
		print("Remove element 0: ");
		str.remove(0);
		print("No exceptions thrown, successful!");
		print("Run tests again now that the single element has been removed");
		print("Size: " + str.size());
		print("First element: " + str.get(0));
		print("Neg element: " + str.get(-1));
		print("toString " + str.toString());
		print("Remove element 0: ");
		str.remove(0);
		print("No exceptions thrown, successful!");
	}
	
	public static void testMultiElementLL() {
		LinkedList<String> str = new LinkedList<String>();
		str.add(new String("test_string_one"));
		str.add(new String("test_string_two"));
		str.add(new String("test_string_three"));
		print("Mutli Element Tests");
		print("Size: " + str.size());
		print("First element: " + str.get(0));
		print("Neg element: " + str.get(-1));
		print("toString " + str.toString());
		print("Remove element 0: ");
		str.remove(0);
		print("No exceptions thrown, successful!");
		print("Add element 1");
		str.add(1, "insert_this_into_1");
		print("Size: " + str.size());
		print("First element: " + str.get(0));
		print("Neg element: " + str.get(-1));
		print("toString " + str.toString());
		print("Remove element 1: ");
		str.remove(1);
		print("No exceptions thrown, successful!");
		print("toString " + str.toString());
	}
	
	
	
	public static void print(Object obj) {
		System.out.println(obj);
	}

}
