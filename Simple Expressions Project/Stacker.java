package csc250_Term_Project;

public class Stacker<T> {
	T data;
	Stacker<T> nextNode;
	
	Stacker(T object){
		this(object, null);
	}
	public Stacker(T object, Stacker<T> node) {
		data = object;
		nextNode = node;
	}
	
	public class Stack<T>{
		private Stack<T> firstNode;
		private Stack<T> lastNode;
		private String name;
		
		// Empty constructor
		public Stack(){
			this("Stack");
		}

		public Stack(String listName) {
			name = listName;
			firstNode = lastNode = null;
		}
		
	}

}
