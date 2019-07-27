package syq.bleg.io;

import syq.bleg.base.exception.MyException;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author shiyuquan
 * Create Time: 2019/7/24 14:15
 */
public class EncoderStream extends OutputStream {

    /**
     * 加密输出流
     */
    private OutputStream os;

    /**
     * 构造函数
     * @param os 给定输出流
     * @param point 业务数据点
     * @param rule 编码解码规则
     */
    public EncoderStream(OutputStream os, byte point, byte rule) {
        this.os = os;
        StreamPreByte preByte = new StreamPreByte(point,rule);
        try {
            this.os.write(preByte.getBytes());
        } catch (IOException e) {
            throw new MyException("error to write to output stream");
        }
    }

    @Override
    public void write(int b) throws IOException {
        os.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        os.write(b, off, len);
    }
}
