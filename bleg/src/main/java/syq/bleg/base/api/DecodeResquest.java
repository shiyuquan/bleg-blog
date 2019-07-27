package syq.bleg.base.api;

import java.lang.annotation.*;

/**
 * 请求解码注解，标识当前请求需要解码
 *
 * @author shiyuquan
 * Create Time: 2019/7/26 11:22
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DecodeResquest {
}
