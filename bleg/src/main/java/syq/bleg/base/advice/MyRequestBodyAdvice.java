package syq.bleg.base.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author shiyuquan
 * Create Time: 2019/7/27 13:34
 */
@ControllerAdvice
public class MyRequestBodyAdvice extends RequestBodyAdviceAdapter {

    /**
     * 全局请求的解码开关
     */
    @Value("${http.request.decode}")
    private boolean support;

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(MyRequestBodyAdvice.class);

    /**
     * 支持的条件
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return support;
    }

    /**
     * 前置处理
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        // byte[] bytes = StreamUtils.copyToByteArray(httpInputMessage.getBody());
        return httpInputMessage;
        // return new MyHttpInputMessage(httpInputMessage.getHeaders(), new ByteArrayInputStream(Base64.getDecoder().decode(bytes)));
    }

    /**
     * 后置处理
     */
    @Override
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    /**
     * 空处理
     */
    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }
}
