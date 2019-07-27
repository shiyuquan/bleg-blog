package syq.bleg.io;

import syq.bleg.base.exception.MyException;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author shiyuquan
 * Create Time: 2019/7/24 14:16
 */
public class DecoderStream extends InputStream {

    /**
     * 原生输入流
     */
    private InputStream inputStream;

    /**
     * 自定义前缀
     */
    private StreamPreByte preByte;

    public DecoderStream(InputStream is) {
        this.inputStream = is;
        byte[] bytes = new byte[6];
        try {
            if (-1 != inputStream.read(bytes)) {
                preByte = new StreamPreByte(bytes);
            } else {
                throw new MyException("fail to load stream, is error sign!");
            }
        } catch (IOException e) {
            throw new MyException("IO error. cause: bad read input stream.");
        }
    }

    /**
     * 获取标志
     */
    public StreamPreByte getPreByte() {
        return preByte;
    }

    /**
     * 获取给定的标志的编码解码规则
     */
    public byte getRule() {
        return getPreByte().getRule();
    }

    @Override
    public int read() throws IOException {
        return inputStream.read();
    }

    @Override
    public int read(@Nonnull byte[] b, int off, int len) throws IOException {
        return super.read(b, off, len);
    }

    @Override
    public int available() throws IOException {
        return inputStream.available();
    }

}
