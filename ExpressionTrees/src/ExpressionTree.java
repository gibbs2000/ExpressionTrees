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

	public ExpressionTree createTree(String[] input) {
		//Add first element to the center of the tree
		ExpressionTree exTree = new ExpressionTree(input[0]);
		
		return exTree;

	}

	@Override
	public TreeNode buildTree(String[] exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int evalTree() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toPrefixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toInfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toPostfixNotation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int postfixEval(String[] exp) {
		// TODO Auto-generated method stub
		return 0;
	}
}
