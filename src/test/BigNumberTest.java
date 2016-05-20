package test;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigNumberTest {
	public static void main(String[] args) {
		BigInteger bigInteger1 = new BigInteger("123123123123123123");
		BigInteger bigInteger2 = new BigInteger("123123123123123123");
		BigInteger bigInteger3 = bigInteger2.add(bigInteger1);
		BigDecimal bigDecimal1 = new BigDecimal("0000123.1230000");
		String string = bigDecimal1.stripTrailingZeros().toPlainString();
		System.out.println(string);
		System.out.println(bigInteger3);
	}
}