package com.mcf.util;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精 确的浮点数运算，包括加减乘除和四舍五入。
 */

public class PriceUtil {

	public static double formatPrice(double price) {
		
		price = MathDoubleUtil.round(price,2);
		
		String strPrice = String.valueOf(Double.valueOf(MathDoubleUtil.mul(price, 100)).intValue());
		int lastNo = Integer.valueOf(strPrice.substring(strPrice.length() - 1, strPrice.length()));
		if (price > 0 && price <= 5) {
			switch (lastNo) {
//			case 0:
//				price = MathDoubleUtil.sub(price,0.01);				
//				break;
			case 1:
				//price = MathDoubleUtil.sub(price,0.02);
				price = MathDoubleUtil.sub(price,0.01);
				break;
			case 2:
			case 3:
			case 4:
				price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "5"), 100);
				break;
			case 6:
			case 7:
			case 8:
				price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "9"), 100);
				break;
			}
		}

		if (price > 5 && price <= 10) {
			switch (lastNo) {
//			case 0:
//				price = MathDoubleUtil.sub(price,0.02);
//				break;
			case 1:
				//price = MathDoubleUtil.sub(price,0.03);
				price = MathDoubleUtil.sub(price,0.01);
				break;
//			case 9:
//				price = MathDoubleUtil.sub(price,0.01);
//				break;
			case 2:
			case 3:
			case 4:
				price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "5"), 100);
				break;
			case 6:
			case 7:
			case 8:
				//price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "8"), 100);
				price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "9"), 100);
				break;
			}

		}
		if (price > 10) {
			switch (lastNo) {
//			case 0:
//				price = MathDoubleUtil.sub(price,0.02);
//				break;
			case 1:
				//price = MathDoubleUtil.sub(price,0.03);
				price = MathDoubleUtil.sub(price,0.01);
				break;
//			case 9:
//				price = MathDoubleUtil.sub(price,0.01);
//				break;
			case 2:
			case 3:
			case 4:
				//price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "8"), 100);
				price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "5"), 100);
				break;
			//case 5:
			case 6:
			case 7:
			case 8:
				//price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "8"), 100);
				price = MathDoubleUtil.div(Double.valueOf(strPrice.substring(0, strPrice.length() - 1) + "9"), 100);
				break;
			}
		}
		return MathDoubleUtil.round(price,2);
	}

	public static void main(String[] args) {
//		 System.out.println(formatPrice(3.30));
//		 System.out.println(formatPrice(3.31));
//		 System.out.println(formatPrice(3.32));
//		 System.out.println(formatPrice(3.33));
//		 System.out.println(formatPrice(3.34));
//		 System.out.println(formatPrice(3.35));
//		 System.out.println(formatPrice(3.36));
//		 System.out.println(formatPrice(3.36));
//		 System.out.println(formatPrice(3.37));
//		 System.out.println(formatPrice(3.38));
//		 System.out.println(formatPrice(3.39));
//		 System.out.println(formatPrice(3.40));
//		
//		
//		 System.out.println(formatPrice(7.30));
//		 System.out.println(formatPrice(7.31));
//		 System.out.println(formatPrice(7.32));
//		 System.out.println(formatPrice(7.33));
//		 System.out.println(formatPrice(7.34));
//		 System.out.println(formatPrice(7.35));
//		 System.out.println(formatPrice(7.36));
//		 System.out.println(formatPrice(7.36));
//		 System.out.println(formatPrice(7.37));
//		 System.out.println(formatPrice(7.38));
//		 System.out.println(formatPrice(7.39));
//		 System.out.println(formatPrice(7.40));
//		 
//		 System.out.println(formatPrice(9.30));
//		 System.out.println(formatPrice(9.31));
//		 System.out.println(formatPrice(9.32));
//		 System.out.println(formatPrice(9.33));
//		 System.out.println(formatPrice(9.34));
//		 System.out.println(formatPrice(9.35));
//		 System.out.println(formatPrice(9.36));
//		 System.out.println(formatPrice(9.36));
//		 System.out.println(formatPrice(9.37));
//		 System.out.println(formatPrice(9.38));
//		 System.out.println(formatPrice(9.98));
//		 System.out.println(formatPrice(9.99));
//		 System.out.println(formatPrice(10.00));
//		 System.out.println(formatPrice(10.01));
//		
//		
//		 System.out.println(formatPrice(17.30));
//		 System.out.println(formatPrice(17.31));
//		 System.out.println(formatPrice(17.32));
//		 System.out.println(formatPrice(17.33));
//		 System.out.println(formatPrice(17.34));
//		 System.out.println(formatPrice(17.35));
//		 System.out.println(formatPrice(17.36));
//		 System.out.println(formatPrice(17.36));
//		 System.out.println(formatPrice(17.37));
//		 System.out.println(formatPrice(17.38));
//		 System.out.println(formatPrice(17.39));
//		 System.out.println(formatPrice(17.40));
//		 
//		 
//		 System.out.println(formatPrice(111.209));
//		 
//		 System.out.println(formatPrice(111.111));
//		 System.out.println(formatPrice(111.114));
//		 System.out.println(formatPrice(111.115));
//		 System.out.println(formatPrice(111.121));
//		 System.out.println(formatPrice(111.161));
		 System.out.println(formatPrice(124.385));
	}
}
