package org.xlyo.anywild.util;

public class MathUtil {
    /**
     * 限制介于最小值和最大值之间的值
     * @param value 要限制的值
     * @param min 最小值
     * @param max 最大值
     * @return 限制后的值
     * @param <TNumber> 要限制的数字类型
     */
    public static <TNumber extends Number & Comparable<TNumber>> TNumber clamp(
            TNumber value, TNumber min, TNumber max) {
        if (value.compareTo(min) < 0) {
            return min;
        } else if (value.compareTo(max) > 0) {
            return max;
        } else {
            return value;
        }
    }
}
