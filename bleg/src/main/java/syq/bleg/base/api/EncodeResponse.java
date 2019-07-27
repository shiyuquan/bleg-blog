package syq.bleg.base.api;

import java.lang.annotation.*;

/**
 * 响应编码注解，标识响应需要加密
 *
 * @author shiyuquan
 * Create Time: 2019/7/26 11:25
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncodeResponse {
}
