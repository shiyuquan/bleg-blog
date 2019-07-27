package syq.bleg.io;

import syq.bleg.base.exception.MyException;

/**
 * 自定义流的前缀标识字节
 * 0 - 3 个标志是默认标志 bleg
 * 第四个标志代表数据的业务点，第五个标志代表编码解码方式
 *
 * @author shiyuquan
 * Create Time: 2019/7/24 14:17
 */
public class StreamPreByte {
    /**
     * 字节标志
     */
    private static final byte[] sign = new byte[4];

    static {
        // 初始化 sign 为 bleg
        sign[0] = 98;
        sign[1] = 108;
        sign[2] = 101;
        sign[3] = 103;
    }

    /**
     * 长度为8的字节
     */
    private byte[] bytes;

    /**
     * 构造函数 传入byts
     */
    public StreamPreByte(byte[] bytes) {
        loadBytes(bytes);
    }

    /**
     * 构造函数 第四个标志代表数据的业务点，第五个标志代表编码解码方式
     * @param point 表数据的业务点
     * @param rule 编码解码方式
     */
    public StreamPreByte(byte point, byte rule) {
        bytes = new byte[6];
        System.arraycopy(sign, 0, bytes, 0, sign.length);
        bytes[4] = point;
        bytes[5] = rule;
    }

    /**
     * @param pointId 数据点
     */
    public StreamPreByte(byte pointId) {
    }

    /**
     * 给定字节数组，并加载
     * @param bytes 字节数组
     */
    private void loadBytes(byte[] bytes) {
        if (isPreByte(bytes)) {
            this.bytes = bytes;
        } else {
            throw new MyException("Unable load bytes");
        }
    }

    /**
     * 判断给定的字节数组是否匹配预定义
     * @param bytes 给定数组
     * @return boolean
     */
    private boolean isPreByte(byte[] bytes) {
        for (int i = 0; i < sign.length; i++) {
            if (sign[i] != bytes[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取数据的业务点标志
     */
    public byte getPoint() {
        return bytes[4];
    }

    /**
     * 获取编码解码规则标志
     */
    public byte getRule() {
        return bytes[5];
    }

    public byte[] getBytes() {
        return bytes;
    }
}
