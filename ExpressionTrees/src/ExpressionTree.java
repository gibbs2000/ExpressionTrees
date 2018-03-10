import java.util.Stack;

/**
 * Expression Tree
 * 
 * @author Sean Gibbons
 *
 */
public class ExpressionTree extends TreeNode implements Expressions {

	/**
	 * Creates an ExpressionTree using a String array
	 * 
	 * @param exp
	 */
	public ExpressionTree(String[] exp) {
		super("");
		TreeNode n = this.buildTree(exp);
		this.setLeft(n.getLeft());
		this.setRight(n.getRight());
		this.setValue(n.getValue());
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

	/**
	 * Creates an ExpressionTree with no branches
	 * 
	 * @param o
	 *            the root of the ExpressionTree to be created
	 */
	public ExpressionTree(Object o) {
		super(o);
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}

	@Override
	public TreeNode buildTree(String[] exp) {
		Stack<TreeNode> stk = new Stack<TreeNode>();
		for (String str : exp) {
			if (!this.isOperation(str)) {
				stk.push(new ExpressionTree(Integer.parseInt(str)));
			} else {

				ExpressionTree right = (ExpressionTree) (stk.pop());
				ExpressionTree left = (ExpressionTree) (stk.pop());
				ExpressionTree node = new ExpressionTree(str, left, right);

				stk.push(node);
			}
		}
		return stk.pop();

	}

	/**
	 * A helper method that checks whether a String is a mathematical operation
	 * 
	 * @param s
	 *            the input to be checked
	 * @return whether a String is a mathematical operation
	 */
	private boolean isOperation(String s) {
		if (s.trim().equals("*") || s.trim().equals("+") || s.trim().equals("-") || s.trim().equals("/")
				|| s.trim().equals("%")) {
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
		case "-":
			return n1 - n2;
		case "/":
			if (n2 != 0)
				return n1 / n2;
			else
				throw new IllegalArgumentException("Cannot divide by 0\n\n");
		case "%":
			return n1 % n2;

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
			return this.getValue().toString() + " " + ((ExpressionTree) this.getLeft()).toPrefixNotation() + " "
					+ ((ExpressionTree) this.getRight()).toPrefixNotation();
		}
	}

	@Override
	public String toInfixNotation() {
		return this.toInfixNotationPar().substring(1, this.toInfixNotationPar().length() - 1);
	}

	/**
	 * Traverses the ExpressionTree and returns a String representation of it in
	 * Infix Notation but with too many parentheses
	 * 
	 * @return a String representation of the TreeExpression in Infix Notation but
	 *         with extra parentheses
	 */
	public String toInfixNotationPar() {
		if (this.getLeft() == null || this.getRight() == null) {
			return this.toString();
		} else {
			return "(" + (((ExpressionTree) this.getLeft()).toInfixNotationPar().toString() + " "
					+ this.getValue().toString() + " " + ((ExpressionTree) this.getRight()).toInfixNotationPar())
							.toString()
					+ ")";
		}

	}

	@Override
	public String toPostfixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return this.toString();
		} else {
			return ((ExpressionTree) this.getLeft()).toPostfixNotation() + " "
					+ ((ExpressionTree) this.getRight()).toPostfixNotation() + " " + this.getValue().toString();
		}
	}

	@Override
	public int postfixEval(String[] exp) {
		Stack<Integer> stk = new Stack<Integer>();
		for (String str : exp) {
			if (!this.isOperation(str)) {
				stk.push(Integer.parseInt(str));
			} else {
				int right = stk.pop();
				int left = stk.pop();
				stk.push(this.performOp(left, right, str));
			}
		}
		return stk.pop();

	}

}
