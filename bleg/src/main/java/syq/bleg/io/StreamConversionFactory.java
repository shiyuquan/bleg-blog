package syq.bleg.io;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流过滤工厂
 *
 * @author shiyuquan
 * Create Time: 2019/7/26 15:50
 */
public interface StreamConversionFactory {

    /**
     * 返回过滤后的输出流
     */
    OutputStream transfer(OutputStream os);

    /**
     * 返回过滤后的输入流
     */
    InputStream transfer(InputStream is);

    /**
     * 默认工厂
     */
    StreamConversionFactory DEFAULT =  new StreamConversionFactory() {
        @Override
        public OutputStream transfer(OutputStream os) {
            return os;
        }

        @Override
        public InputStream transfer(InputStream is) {
            return is;
        }
    };
}
