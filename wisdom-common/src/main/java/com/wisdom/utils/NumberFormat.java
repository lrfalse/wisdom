package com.wisdom.utils;

import java.math.BigDecimal;

public class NumberFormat {
	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param v 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0){
			throw new IllegalArgumentException(
					"不能为小于0");}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static double round(String v, int scale) {
		if (scale < 0){
			throw new IllegalArgumentException(
					"不能为小于0");}
		BigDecimal b = new BigDecimal(v);
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static void main(String[] arg){
		System.out.println(String.format("%.8f",round("0.0001035359999999999926212357337362846010364592075347900390625",8)));
	}

}
