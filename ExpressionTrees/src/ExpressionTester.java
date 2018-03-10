import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main Tester for ExpressionTree
 * 
 * @author Sean Gibbons
 *
 */
public class ExpressionTester {
	/**
	 * Main method for testing ExpressionTree
	 * 
	 * @param args
	 *            optional String parameters for the input file
	 */
	public static void main(String[] args) {

		String fName = "postFixExpressions.txt";
		String outputLoc = "myAnswers.txt";
		if (args.length >= 1) {

			fName = args[0];
		}

		Scanner input = fileToScanner(fName);
		if (input == null) {

			System.out.println("Default file not found. \nPlease enter a valid file name ending with \".txt\": ");
			Scanner in = new Scanner(System.in);
			fName = in.next();
			int counter = 0;
			while (fName.length() < 5 || !".txt".equals(fName.substring(fName.length() - 4))) {
				if (counter < 5) {
					System.out.println("Invalid file name. \nPlease enter a valid file name ending with \".txt\": ");
				} else {
					System.out.println(
							"You are really trying my patience here.\nPlease enter a valid file name ending with \".txt\":  ");
				}
				fName = in.next();
				counter++;

			}
			in.close();
			input = fileToScanner(fName);
		}

		PrintWriter output = outputFile(outputLoc);
		ArrayList<String[]> expressions = makeExpressions(input);
		output.println(ExpressionTree.getHeader());
		for (String[] exp : expressions) {
			output.println(testExpressionTrees(exp));
		}
		output.print(ExpressionTree.getFooter());
		output.close();
		input.close();
	}

	/**
	 * Takes in a Scanner and returns an ArrayList of String arrays, each of which
	 * will be tested
	 * 
	 * @param input
	 *            the Scanner input to test
	 * @return an ArrayList of String arrays to be converted into ExpressionTrees to
	 *         be tested
	 */
	public static ArrayList<String[]> makeExpressions(Scanner input) {
		ArrayList<String[]> expressions = new ArrayList<String[]>();

		while (input.hasNextLine()) {
			String l = input.nextLine().trim();
			ArrayList<String> exp = new ArrayList<String>();
			if (!"".equals(l)) {
				// checks for empty lines
				int i = 0;
				for (; i < l.length();) {
					// Checks for values within the line and adds them to the expression line
					if (l.indexOf(" ", i) != -1) {
						exp.add(l.substring(i, l.indexOf(" ", i)));
						i = l.indexOf(" ", i) + 1;

					} else
						break;

				}
				if (i != 0) {// ensures the last value is added (for debugging)
					exp.add(l.substring(i));
				}
				expressions.add(exp.toArray(new String[exp.size()]));
				// Adds all of the lines into the overall
				// ExpressionTree ArrayList for testing
			}
		}
		return expressions;
	}

	/**
	 * Tests the ExpressionTree problem and returns a String representation of the
	 * result
	 * 
	 * @param exp
	 *            the array of Strings to be converted to an ExpressionTree to be
	 *            tested
	 * @return a String representation of the test results
	 */
	public static String testExpressionTrees(String[] exp) {
		ExpressionTree example = new ExpressionTree(exp);
		String output = "";

		output += "Testing commencing...\nGiven expression \"";
		for (String s : exp) {
			output += s + " ";
		}
		output += "\" \n";
		// Test the different notations
		output += "Testing Notations \nPostfix Notation\n" + example.toPostfixNotation() + "\nPrefix Notation\n"
				+ example.toPrefixNotation() + "\nInfix Notation\n" + example.toInfixNotation();

		// Blank lines
		output += "\n\n";

		// Test evaluation
		output += "Evaluating Expression using the ExpressionTree structure: " + example.evalTree() + "\n";

		output += "Evaluating Expression using the postFixEval method " + example.postfixEval(exp) + "\n";
		output += "Are these results the same? " + (example.evalTree() == (example.postfixEval(exp))) + "\n";

		output += "Testing complete\n\n\n";

		return output;
	}

	/**
	 * Converts the given file to Scanner
	 * 
	 * @param fName
	 *            The String name of a file
	 * 
	 * @return A Scanner of the file with the given file name
	 */
	public static Scanner fileToScanner(String fName) {

		File fileName = new File(fName);
		Scanner words = null;

		try {
			words = new Scanner(fileName);
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to Open file: " + fName + ", please see output");
			return null;

		}
		if (!words.hasNext())
			throw new IllegalArgumentException("File is empty");
		return words;

	}

	/**
	 * Creates a file of the given name
	 * 
	 * @param fName
	 *            The name of the file to be created
	 * @return A PrintWriter of the same name as fName which can be manipulated and
	 *         then saved
	 */
	public static PrintWriter outputFile(String fName) {
		File fileName = new File(fName);

		PrintWriter output = null;

		try {
			output = new PrintWriter(fileName);
		} catch (FileNotFoundException ex) {
			System.out.println("Cannot open " + fName);
			return null;

		}

		return output;
	}
}
