
public class ExpressionTester {
	public static void main(String[] args) {
		String[] beginTest = { "1", "2", "+", "3", "+" };
		ExpressionTree tree = new ExpressionTree("f");
		tree = tree.buildTree(beginTest);
		System.out.println(tree.toPrefixNotation());
		System.out.println(tree.evalTree());
	}
}
