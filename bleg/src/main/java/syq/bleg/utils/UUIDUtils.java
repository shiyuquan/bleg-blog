package syq.bleg.utils;

import java.util.UUID;

/**
 * UUID 工具类
 * @author shiyuquan
 * @date 2019/4/25 18:04
 */
public class UUIDUtils {
    private UUIDUtils() {}

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
