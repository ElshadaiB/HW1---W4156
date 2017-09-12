
public class Problem1 {

	public static void main(String[] args) {
		ExpressionTree x = new ExpressionTree("3 4 * 8 4 / +");
		System.out.println(x.prefix() + " = " + x.eval());
		System.out.println(x.postfix());
		System.out.println(x.infix());
	}

}
