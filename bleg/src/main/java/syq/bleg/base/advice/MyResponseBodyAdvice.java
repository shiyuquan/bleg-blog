package syq.bleg.base.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import syq.bleg.base.MyResult;

/**
 * @author shiyuquan
 * Create Time: 2019/7/27 17:15
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * 全局请求的解码开关
     */
    @Value("${http.response.encode}")
    private boolean support;

    /**
     * Whether this component supports the given controller method return type
     * and the selected {@code HttpMessageConverter} type.
     *
     * @param returnType    the return type
     * @param converterType the selected converter type
     * @return {@code true} if {@link #beforeBodyWrite} should be invoked;
     * {@code false} otherwise
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return false;
    }

    /**
     * Invoked after an {@code HttpMessageConverter} is selected and just before
     * its write method is invoked.
     *
     * @param body                  the body to be written
     * @param returnType            the return type of the controller method
     * @param selectedContentType   the content type selected through content negotiation
     * @param selectedConverterType the converter type selected to write to the response
     * @param request               the current request
     * @param response              the current response
     * @return the body that was passed in or a modified (possibly new) instance
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        MyResult result = (MyResult) body;
        Object data = result.getData();

        if (null == data) {
            return body;
        }

        String dataStr = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dataStr = objectMapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // result.setData(new String(Base64.getEncoder().encode(dataStr.getBytes())));
        result.setData(dataStr.getBytes());

        return result;
    }
}
