package csc250_Term_Project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;

public class SimpleExpression extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String input;
	static String[] token; // Holds the variables and stuff like that ...
	static String[] tokenValues; // Holds the respective value of the variable
									// ...
	String[] NINTEGERRETURN = { "null" };
	String INTEGERRETURN = "null";
	String VARIABLERETURN = "null";
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JLabel label = new JLabel("Simple Expression");
	JLabel labeler = new JLabel(": ");
	JTextField field = new JTextField(10);
	JButton button = new JButton("Enter");
	JFrame frame = new JFrame();

	String intTest = "null";
	
	JDialog dialog = new JDialog();

	MyStack theStack = new MyStack(30);

	/**
	 * 
	 */
	private void createInterface() {
		panel.setLayout(new GridLayout(0, 1));
		panel.setLocation(10, 10);
		panel2.setLocation(40, 40);
		this.setContentPane(panel);
		panel.add(label);
		panel.add(field);
		panel.add(labeler);
		panel.add(button);

		field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String input = field.getText();
				field.setText(input);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Clean the variables in case the user puts in multiple
				// values.

				String input = field.getText();
				labeler.setText(input);
				input = labeler.getText();
				System.out.println(input);
				if (inputValidator(input) == "String") {
					JOptionPane.showMessageDialog(frame, input);
				} else if (inputValidator(input) == "Integer") {
					JOptionPane.showMessageDialog(frame, input);
				}
				
				/**
				 * Calling the methods ...
				 */
				// Tokenizes the user's input
				tokenizer(input);
				stringVerfier();
				infixToPostfix(input);
				

			}
		});

		setSize(400, 250);
		setBounds(0, 0, 400, 250);
		setTitle("Simple Expression");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	/**
	 * 
	 * @param s
	 */
	public void tokenizer(String s) {
		s.toLowerCase(); // Converts all the strings
							// to lower case
		s.replaceAll("\\s+", ""); // Removes any white space
		// TODO: Have multiple splitters ...
		// [+-/*()]
		token = s.split("");
		tokenValues = s.split("");
		
		// Case 1: If the first number is negative
		if (isNegativeInt(s) == true) {
			token[1] = "-" + token[1];
		}

		// Case 2: Negative number in between the array
		for (int i = 0; i < token.length - 1; i++) {
			if ((inputValidator(token[i]) == "Operator")
					&& (token[i + 1].charAt(0) == '-')
					&& (inputValidator(token[i + 2]) == "Integer")) {
				token[i + 2] = "-" + token[i + 2];
				System.out.println("YEAH");
			}

		}
	}

	/**
	 * 
	 * @param n
	 * @return
	 */
	public boolean isNegativeInt(String n) {
		// return ((n + "").charAt(0) == '-'); -- check if a number is negative.
		// Case 1
		if ((n + "").charAt(0) == '-'
				&& inputValidator(Character.toString(n.charAt(1))) == "Integer") {
			return true;
		}

		return false;
		/**
		 * The only cases a number is going to be negative is: 1. if the
		 * negative sign is at the very front 2. The the negative sign is in
		 * between an operator and an integer
		 */
	}

	public boolean isNegative2(String n) {
		// Case 2
		if (inputValidator(Character.toString(n.charAt(0))) == "Operator"
				&& (n.charAt(1) == '-')
				&& inputValidator(Character.toString(n.charAt(2))) == "Integer") {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static int priorityChecker(String s) {
		int priority = 0;
		switch (s) {
		case "+":
			priority = 3;
			break;
		case "-":
			priority = 3;
			break;
		case "(":
			priority = 1;
			break;
		case ")":
			priority = 1;
			break;
		case "*":
			priority = 2;
			break;
		case "/":
			priority = 2;
			break;
		case "%":
			priority = 2;
			break;
		default:
			break;
		}
		return priority;
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public static String inputValidator(String s) {
		String v = "null";
		char[] ST = s.toCharArray();
		for (char c : ST) {
			if (Character.isLetter(c)) {
				v = "String";
				
			}
			if (Character.isDigit(c)) {
				v = "Integer";
			}
			if (Character.toString(c).matches("[,/+%-*]")) {
				v = "Operator";
		
			}
		}
		return v;
	}
 
	public void stringVerfier() {
		for (int n = 0; n < token.length; n++) {
			// In case of Integer ...
		//System.out.println(infixToPostfix(token[n]));
			if (inputValidator(token[n]) == "Integer") {
				if ((INTEGERRETURN == "null")) {
				INTEGERRETURN = token[n];
				} else {
					
					INTEGERRETURN = INTEGERRETURN + "," + token[n];
//					int counter = 0;
//					counter ++;
//					System.out.println(counter);
//					while(token[n]=="Integer"){
//						//INTEGERRETURN = token[n];
//						
//					}
				}
//				while(token[n].charAt(n)>='0' && token[n].charAt(n)<='9'){
//					INTEGERRETURN +=token[n];
//				}

			}

			if (inputValidator(token[n]) == "String") {
				VARIABLERETURN = token[n];

//				if (variableDuplicate(token[n]) == false) {
					String s = JOptionPane.showInputDialog(VARIABLERETURN);
//					// System.out.println(variableChecker(n));
//					if (variableChecker(n) == 1) {
					// TODO: Check if the input is an integer .... and throw an exception
					if (s.matches("\\d")){
						tokenValues[n] = s;
					} else{
						JOptionPane.showMessageDialog(frame,"Please enter a valid input for the value of the variable!");
						tokenValues[n] = "Invalid";
					}
					
//					} else {
//						// Throw an exception
//						JOptionPane.showMessageDialog(frame,
//								"Please enter a variable name less than 8");
//						System.out
//								.println("Please enter a variable name less than 8");
//					}
//				} else {
//					// fix code
//					token[n].replace(token[n], " ");
//					String s = JOptionPane.showInputDialog(VARIABLERETURN);
//					// System.out.println(variableChecker(n));
//					if (variableChecker(n) == 1) {
//						tokenValues[n] = s;
//					} else {
//						// Throw an exception
//						JOptionPane.showMessageDialog(frame,
//								"Please enter a variable name less than 8");
//						System.out
//								.println("Please enter a variable name less than 8");
//					}
//					JOptionPane.showMessageDialog(frame,
//							"a variable is repeated");
					// return;
//				}

				// TODO: Cleanup variables as soon as the user finishes entering
				// the values for the variables ...
				panel.add(new JLabel(token[n] + ": " + tokenValues[n]));
				panel.revalidate();
				panel.repaint();
				frame.pack();

				/**
				 * Parallel array for having the values of the integer.
				 */
				System.out.println(token[n] + " -- " + tokenValues[n]);

			}

		}
		JOptionPane.showMessageDialog(frame, INTEGERRETURN);
	}

	/**
	 * Verifies if the variable name is not repeated and less than 8 characters.
	 * 1 == Positive 2 == Negative
	 * 
	 * @param index
	 * @return
	 */
	public static int variableChecker(int index) {
		char[] ST = token[index].toCharArray();
		if ((token[index].length() <= 8)) {

			for (int i = 0; i < token.length; i++) {
				// Checks for repetion
				if (token[i] != token[index]) {
					// Check if the name has only characters. -- Buggy!!! TODO:
					// Fix this code!!
					if (inputValidator(token[index]) == "String") {
						return 1;
					}
				}

			}
		}
		return 0;
		
	}

	// Variable repetion verifier
	/**
	 * 
	 * @param s
	 * @return
	 */
	public static boolean variableDuplicate(String s) {
		char dup = s.charAt(0);
		int counter = 0;
		for (int i = 0; i < token.length - 1; i++) {
			if (token[i].charAt(0) == dup) {
				counter++;
				System.out.println(counter);
			}
		}
		if (counter > 1) {
			return true;
		}
		return false;
	}
	// Starting Stage ...
	/**
	 * 
	 * @param infix
	 * @return
	 */
	public static String infixToPostfix(String infix) {
		// SUPER BUGGY!!!!!!
		Stack<String> operatorStack = new Stack<String>();
		ArrayList<String> postfix = new ArrayList<String>();
		char[] inFixChar = infix.toCharArray();
		/** 
		 * Work on the algorithm as soon as possible + Implement ...
		 */
		for (char c: inFixChar){
//			switch(c){
//				case '+':
//					operatorStack.push(Character.toString(c));
//					break;
//				case '*':
//					operatorStack.push(Character.toString(c));
//					break;
//				case '/':
//					operatorStack.push(Character.toString(c));
//					break;
//				case '-':
//					operatorStack.push(Character.toString(c));		
//			}
//			if(c=='('){
//				
//				if(postfix.isEmpty()){
//					do{
//						postfix.add(Character.toString(c));
//					}while((Character.toString(c) == "String") || (Character.toString(c) == "Integer"));
//				}
//			}
			
			if(inputValidator(Character.toString(c)) == "String"){
				postfix.add(Character.toString(c));
			}
			if(inputValidator(Character.toString(c)) == "Integer"){
				postfix.add(Character.toString(c));
			}
			if(inputValidator(Character.toString(c)) == "Operator"){
				int p =	priorityChecker(Character.toString(c));
				switch(p){
				case 1:
					operatorStack.push(Character.toString(c));
					break;
				case 2: 
					if (operatorStack.isEmpty()){
						operatorStack.push(Character.toString(c));
					} else{
						String s =operatorStack.pop();
						operatorStack.push(Character.toString(c));
						operatorStack.push(s);
					}
					
					break;
				case 3:
					operatorStack.push(Character.toString(c));
					break;
				default:
					return "Error";					
				}
			}
		}
		postfix.addAll(operatorStack);
		System.out.println(postfix);
		
		return postfix.toString();
	}
	/**
	 * 
	 * @param equation
	 * @return
	 */
	public static int evaluator(String equation){
		int solution =0; 
		return solution;
	}
	
	public static void main(String[] args) {
		SimpleExpression se = new SimpleExpression();
		se.createInterface();
	}

}
