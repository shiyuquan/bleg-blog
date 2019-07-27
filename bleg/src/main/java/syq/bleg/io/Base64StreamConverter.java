package syq.bleg.io;

import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author shiyuquan
 * Create Time: 2019/7/26 16:09
 */
public class Base64StreamConverter implements StreamConversionFactory {

    @Override
    public OutputStream transfer(OutputStream os) {
        return new BASE64EncoderStream(os);
    }

    @Override
    public InputStream transfer(InputStream is) {
        return new BASE64DecoderStream(is);
    }
}
