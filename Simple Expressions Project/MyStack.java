package csc250_Term_Project;

public class MyStack {

		  private int maxSize;
		  private long[] stackArray;
		  private int top;

		  public MyStack(int s) {
		    maxSize = s;
		    stackArray = new long[maxSize];
		    top = -1;
		  }

		  public void push(long j) {
		    stackArray[++top] = j;
		  }

		  public long pop() {
		    return stackArray[top--];
		  }

		  public long peek() {
		    return stackArray[top];
		  }

		  public boolean isEmpty() {
		    return (top == -1);
		  }

		  public boolean isFull() {
		    return (top == maxSize - 1);
		  }

		  public static void main(String[] args) {
//			  String string = "55s33";
//			    String[] piece = string.split("[\\D]+");
//			    for (int j = 0; j < piece.length; j++) {
//			        if(piece[j].trim().length() > 0) {
//			            System.out.println(piece[j]);
//			        }
//			    }
			  
		    MyStack theStack = new MyStack(10); // make new stack
		    theStack.push(20);
		    theStack.push(40);
		    theStack.push(60);
		    theStack.push(80);
		    theStack.push(800);
		    theStack.pop();
		    theStack.pop();
		    theStack.pop();
		    while (!theStack.isEmpty()) {
		      long value = theStack.pop();
		      System.out.print(value);
		      System.out.print(" ");
		    }
		    System.out.println("");
		  }
		}

