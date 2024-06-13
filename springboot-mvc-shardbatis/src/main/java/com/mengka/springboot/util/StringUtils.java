package com.mengka.springboot.util;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public class StringUtils {
    private static final String STRING_NULL = "null";

    public StringUtils() {
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isEmpty(String... strings) {
        String[] var4 = strings;
        int var3 = strings.length;

        for(int var2 = 0; var2 < var3; ++var2) {
            String string = var4[var2];
            if(isEmpty(string)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isEmpty(Object object) {
        return object == null || object.toString().length() == 0;
    }

    public static boolean isEmpty(Object... objects) {
        Object[] var4 = objects;
        int var3 = objects.length;

        for(int var2 = 0; var2 < var3; ++var2) {
            Object object = var4[var2];
            if(isEmpty(object)) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    public static boolean isNotEmpty(String... strings) {
        return !isEmpty(strings);
    }

    public static boolean isBlank(String str) {
        int strLen;
        if(str != null && (strLen = str.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isBlank(Object object) {
        int strLen;
        if(object != null && (strLen = object.toString().length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if(!Character.isWhitespace(object.toString().charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    public static boolean isNotBlank(Object object) {
        return !isBlank(object);
    }

    public static boolean equals(String str1, String str2) {
        return str1 == null?str2 == null:str1.equals(str2);
    }

    public static boolean equalsIgnoreCase(String str1, String str2) {
        return str1 == null?str2 == null:str1.equalsIgnoreCase(str2);
    }

    public static boolean isNumeric(String str) {
        if(str != null && str.length() != 0) {
            int sz = str.length();

            for(int i = 0; i < sz; ++i) {
                if(!Character.isDigit(str.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public static String convertToNull(String str) {
        return "null".equalsIgnoreCase(str)?null:str;
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }

    public static boolean isNotEmpty(Object... objects) {
        return !isEmpty(objects);
    }

    public static String valueOf(Object obj) {
        return obj == null?null:String.valueOf(obj);
    }

    public static String nullToBlank(Object object) {
        return object == null?"":valueOf(object);
    }
}