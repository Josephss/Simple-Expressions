package csc250_Term_Project;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer_Tester  {
	     private String input;

	     public Tokenizer_Tester(String input_) { input = input_.trim(); }

	     private char peek(int i) {
	        return i >= input.length() ? '\0' : input.charAt(i);
	     }

	     private String consume(String... arr) {
	        for(String s : arr)
	           if(input.startsWith(s))
	              return consume(s.length());
	        return null;
	     }

	     private String consume(int numChars) {
	        String result = input.substring(0, numChars);
	        input = input.substring(numChars).trim();
	        return result;
	     }

	     private String literal() {
	        for (int i = 0; true; ++i)
	           if (!Character.isDigit(peek(i)))
	              return consume(i);
	     }

	     public List<String> tokenize() {
	        List<String> res = new ArrayList<String>();
	        if(input.isEmpty())
	           return res;

	        while(true) {
	           res.add(literal());
	           if(input.isEmpty())
	              return res;

	           String s = consume("+", "-", "/", "*", "^");
//	           if(s == null)
//	              throw new RuntimeException("Syntax error " + input);
	           res.add(s);
	        }
	     }
	     

	     public static void main(String[] args) {
	    	 Tokenizer_Tester t = new Tokenizer_Tester("578+223-52");
	        System.out.println(t.tokenize());
	     }   
	  }

