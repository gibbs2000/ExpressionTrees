import java.util.Stack;

/**
 * Expression Tree
 * 
 * @author Sean Gibbons
 *
 */
public class ExpressionTree extends TreeNode implements Expressions {
	/**
	 * Creates an ExpressionTree using the superclass constructor
	 * 
	 * @param initValue
	 *            the value for the root of the ExpressionTree
	 */
	public ExpressionTree(Object initValue) {
		super(initValue);
	}

	/**
	 * Creates an ExpressionTree using the superclass constructor
	 * 
	 * @param initValue
	 *            the value for the root of the ExpressionTree
	 * @param initLeft
	 *            the value for the left branch of the ExpressionTree
	 * @param initRight
	 *            the value for the right branch of the ExpressionTree
	 */
	public ExpressionTree(Object initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}

	@Override
	public ExpressionTree buildTree(String[] exp) {
		Stack<Object> stk = new Stack<Object>();
		for (String str : exp) {
			if (this.isOperation(str)) {
				TreeNode node = new ExpressionTree(str, new ExpressionTree(stk.pop()), new ExpressionTree(stk.pop()));
				stk.push(node);
			} else {
				stk.push(Integer.parseInt(str));
			}
		}
		return new ExpressionTree(stk.pop());

	}

	/**
	 * A helper method that checks whether a String is a mathematical operation
	 * 
	 * @param s
	 *            the input to be checked
	 * @return whether a String is a mathematical operation
	 */
	private boolean isOperation(String s) {
		if (s.trim().equals("*") || s.trim().equals("+")) {
			return true;
		} else
			return false;
	}

	/**
	 * A helper method that performs a mathematical operation given two integers and
	 * a String operation and returns the result
	 * 
	 * @param n1
	 *            the first number
	 * @param n2
	 *            the second number
	 * @param operation
	 *            the mathematical operation to be performed
	 * @return the resultant of performing the mathematical operation
	 */
	private int performOp(int n1, int n2, String operation) {
		switch (operation.trim()) {
		case "*":
			return n1 * n2;
		case "+":
			return n1 + n2;
		default:
			return n1 + n2;
		}
	}

	@Override
	public int evalTree() {
		if (this.getLeft() == null || this.getRight() == null) {
			return Integer.parseInt(this.getValue().toString());
		} else {
			return performOp(((ExpressionTree) this.getLeft()).evalTree(),
					((ExpressionTree) this.getRight()).evalTree(), this.getValue().toString());
		}
	}

	@Override
	public String toPrefixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return this.toString();
		} else {
			return this.toString() + ((ExpressionTree) this.getLeft()).toPrefixNotation()
					+ ((ExpressionTree) this.getRight()).toPrefixNotation();
		}
	}

	@Override
	public String toInfixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return this.toString();
		} else {
			return "(" + ((ExpressionTree) this.getLeft()).toPrefixNotation() + (String) this.getValue()
					+ ((ExpressionTree) this.getRight()).toPrefixNotation() + ")";
		}
	}

	@Override
	public String toPostfixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return this.toString();
		} else {
			return ((ExpressionTree) this.getLeft()).toPostfixNotation()
					+ ((ExpressionTree) this.getRight()).toPostfixNotation() + this.toString();
		}
	}

	@Override
	public int postfixEval(String[] exp) {
		TreeNode tree = new ExpressionTree("str");
		tree = ((ExpressionTree) tree).buildTree(exp);
		return ((ExpressionTree) tree).evalTree();
	}
}
