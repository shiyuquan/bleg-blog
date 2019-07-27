package syq.bleg.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author shiyuquan
 * @date 2019/4/25 18:08
 */
public class DateTimeUtils {
    private DateTimeUtils() {}

    public static String now() {
        return now("yyyy-MM-dd HH:mm:ss");
    }

    public static String now(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将格式化的日期的数字提取出来
     * @return String
     */
    public static String timeToNum() {
        String t = now("yyyy-MM-dd HH:mm:ss");
        return timeToNum(t);
    }

    /**
     * 将格式化的日期的数字提取出来
     * @param str 格式化的时间字符串
     * @return String
     */
    public static String timeToNum(String str) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                out.append(str.charAt(i));
            }
        }
        return out.toString();
    }
}
