package com.qishiyun.familyedu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String DATE_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

    private static SimpleDateFormat simpleDateFormat;

    public static String DateToString(Date source) {

        simpleDateFormat = new SimpleDateFormat(DATE_TO_STRING_DETAIAL_PATTERN);

        return simpleDateFormat.format(source);

    }
}
