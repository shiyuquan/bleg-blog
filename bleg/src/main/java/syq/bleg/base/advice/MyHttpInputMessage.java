package syq.bleg.base.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * 自定义 httpInputMessage 用于http传输时对数据进行统一操作
 * 
 * @author shiyuquan
 * Create Time: 2019/7/27 13:57
 */
public class MyHttpInputMessage implements HttpInputMessage {

    /**
     * http 头
     */
    private HttpHeaders headers;

    /**
     * body 是个输入流
     */
    private InputStream body;

    /**
     * 构造函数
     */
    public MyHttpInputMessage(HttpInputMessage httpInputMessage) throws IOException {
        this.headers = httpInputMessage.getHeaders();
        this.body = httpInputMessage.getBody();
    }

    public MyHttpInputMessage(HttpHeaders headers, InputStream body) {
        this.headers = headers;
        this.body = body;
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
