import java.util.Stack;

/**
 * Expression Tree
 * 
 * @author Sean Gibbons
 *
 */
public class ExpressionTree extends TreeNode implements Expressions {

	public ExpressionTree(Object initValue) {
		super(initValue);
	}

	public ExpressionTree(Object initValue, TreeNode initLeft, TreeNode initRight) {
		super(initValue, initLeft, initRight);
	}

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
		return (ExpressionTree) new TreeNode(stk.pop());

	}

	private boolean isOperation(String s) {
		if (s.trim().equals("*") || s.trim().equals("+")) {
			return true;
		} else
			return false;
	}

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
			return Integer.parseInt((String) this.getValue());
		} else {
			return performOp(((ExpressionTree) this.getLeft()).evalTree(),
					((ExpressionTree) this.getRight()).evalTree(), (String) this.getValue());
		}
	}

	@Override
	public String toPrefixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return (String) this.getValue();
		} else {
			return (String) this.getValue() + (String) ((ExpressionTree) this.getLeft()).toPrefixNotation()
					+ (String) ((ExpressionTree) this.getRight()).toPrefixNotation();
		}
	}

	@Override
	public String toInfixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return (String) this.getValue();
		} else {
			return "(" + (String) ((ExpressionTree) this.getLeft()).toPrefixNotation() + (String) this.getValue()
					+ (String) ((ExpressionTree) this.getRight()).toPrefixNotation() + ")";
		}
	}

	@Override
	public String toPostfixNotation() {
		if (this.getLeft() == null || this.getRight() == null) {
			return (String) this.getValue();
		} else {
			return (String) ((ExpressionTree) this.getLeft()).toPostfixNotation()
					+ (String) ((ExpressionTree) this.getRight()).toPostfixNotation() + (String) this.getValue();
		}
	}

	@Override
	public int postfixEval(String[] exp) {
		TreeNode tree = new ExpressionTree("str");
		tree = ((ExpressionTree) tree).buildTree(exp);
		return ((ExpressionTree) tree).evalTree();
	}
}
