package syq.bleg.io;

import syq.bleg.constants.StreamConstant;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 流转换器
 *
 * @author shiyuquan
 * Create Time: 2019/7/26 15:32
 */
public interface StreamConverter {

    /**
     * 传入原始输入流，转换为解码流
     */
    InputStream getDecodeStream(InputStream is);

    /**
     * 传入原始输出流，转换为编码输出流
     * @param point 业务数据点
     * @param rule 编码解码规则
     */
    OutputStream getEncodeStream(OutputStream os, byte point, byte rule);

    /**
     * 默认无
     */
    default OutputStream getEncodeStream(OutputStream os, byte point) {
        return getEncodeStream(os, point, StreamConstant.RULE_NO);
    }
}
