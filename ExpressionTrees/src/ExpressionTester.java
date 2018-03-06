import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ExpressionTester {
	public static void main(String[] args) {
		String[] beginTest = { "1", "2", "+", "3", "+" };
		ExpressionTree tree = new ExpressionTree("f");
		tree = tree.buildTree(beginTest);
		System.out.println(tree.toPrefixNotation());

		String fName = "postFixExpressions.txt";
		String outputLoc = "myAnswers.txt";
		if (args.length >= 1) {

			fName = args[0];
		}

		Scanner input = fileToScanner(fName);
		if (input == null) {

			System.out.println(
					"You did not input a file name." + " \nPlease enter a valid file name ending with \".txt\": ");
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
		for (String[] exp : expressions) {
			output.println(testExpressionTrees(exp));
		}
		output.close();
		input.close();
	}

	public static ArrayList<String[]> makeExpressions(Scanner input) {
		ArrayList<String[]> expressions = new ArrayList<String[]>();

		while (input.hasNextLine()) {
			String l = input.nextLine();
			ArrayList<String> exp = new ArrayList<String>();
			
			expressions.add(exp.toArray(new String[exp.size()]));

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
		ExpressionTree example = new ExpressionTree("");
		String output = "";
		example = example.buildTree(exp);

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
