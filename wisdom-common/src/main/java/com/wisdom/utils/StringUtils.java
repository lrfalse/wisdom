//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.wisdom.utils;

import com.google.common.base.Strings;
import java.util.Locale;
import java.util.Random;

public class StringUtils {
    private StringUtils() {
    }

    public static String substr(String string, int start, int end) {
        if (Strings.isNullOrEmpty(string)) {
            return "";
        } else {
            try {
                return string.length() <= end ? string.substring(start) : string.substring(start, end);
            } catch (IndexOutOfBoundsException var4) {
                return "";
            }
        }
    }

    public static String substr(Object obj, int start, int end) {
        return substr(obj == null ? "" : obj.toString(), start, end);
    }

    public static String geneVcode(int n) {
        Random random = new Random();
        StringBuilder nine = new StringBuilder();

        int randomNum;
        for(randomNum = 0; randomNum < n; ++randomNum) {
            nine.append("9");
        }

        randomNum = random.nextInt(Integer.parseInt(nine.toString()));
        String format = "%0" + n + "d";
        return String.format(format, randomNum);
    }

    public static String underscoreName(String camelName) {
        if (camelName == null) {
            return "";
        } else {
            StringBuilder result = new StringBuilder();
            result.append(lowerCaseName(camelName.substring(0, 1)));

            for(int i = 1; i < camelName.length(); ++i) {
                String s = camelName.substring(i, i + 1);
                String slc = lowerCaseName(s);
                if (!s.equals(slc)) {
                    result.append("_").append(slc);
                } else {
                    result.append(s);
                }
            }

            return result.toString();
        }
    }

    private static String lowerCaseName(String name) {
        return name.toLowerCase(Locale.US);
    }

    public static boolean isBlank(CharSequence cs) {
        return org.apache.commons.lang3.StringUtils.isBlank(cs);
    }

    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }
}
