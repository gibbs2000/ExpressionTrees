/**
 * An interface for use in the ExpressionsTree project
 * 
 * @author Mrs. Kelly/Sean Gibbons
 *
 */
public interface Expressions {
	/**
	 * Takes in an array of Strings in postfix notation order and builds and returns
	 * an ExpressionTree
	 * 
	 * @param exp
	 *            an array of Strings in postfix notation
	 * @return an ExpressionTree made of the input
	 */
	TreeNode buildTree(String[] exp);

	/**
	 * Evaluates the ExpressionTree and returns the answer
	 * 
	 * @return the value of the ExpressionTree
	 */
	int evalTree();

	/**
	 * Traverses the ExpressionTree and returns a String representation of it in
	 * Prefix Notation
	 * 
	 * @return a String representation of the TreeExpression in Prefix Notation
	 */
	String toPrefixNotation();

	/**
	 * Traverses the ExpressionTree and returns a String representation of it in
	 * Infix Notation
	 * 
	 * @return a String representation of the TreeExpression in Infix Notation
	 */
	String toInfixNotation();

	/**
	 * Traverses the ExpressionTree and returns a String representation of it in
	 * Postfix Notation
	 * 
	 * @return a String representation of the TreeExpression in Postfix Notation
	 */
	String toPostfixNotation();

	/**
	 * Takes in an array of Strings which are in post-fix notation and evaluates the
	 * expression
	 * 
	 * @param exp
	 * @return
	 */
	int postfixEval(String[] exp);
}