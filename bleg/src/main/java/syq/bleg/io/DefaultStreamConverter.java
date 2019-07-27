package syq.bleg.io;

import syq.bleg.base.exception.MyException;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.function.Supplier;

/**
 * @author shiyuquan
 * Create Time: 2019/7/26 15:43
 */
public class DefaultStreamConverter implements StreamConverter {

    /**
     * 流过滤工厂
     */
    private StreamConversionFactory[] conversionFactories = new StreamConversionFactory[16];

    public DefaultStreamConverter() {
        init();
    }

    /**
     * 初始化工厂
     */
    private void init() {
        conversionFactories[0] = StreamConversionFactory.DEFAULT;
        conversionFactories[1] = new Base64StreamConverter();

    }

    /**
     * 解码转换
     */
    @Override
    public InputStream getDecodeStream(InputStream is) {
        DecoderStream ds = new DecoderStream(is);
        byte rule = ds.getRule();
        return transform(() -> conversionFactories[getRuleIndex(rule)].transfer(ds));
    }

    /**
     * 编码转换
     */
    @Override
    public OutputStream getEncodeStream(OutputStream os, byte point, byte rule) {
        EncoderStream es = new EncoderStream(os, point, rule);
        return conversionFactories[getRuleIndex(rule)].transfer(es);
        // return transform(() -> conversionFactories[getRuleIndex(rule)].transfer(es));
    }

    /**
     * 执行转换器 获取结果
     * @param t   类型提供者
     * @param <T> 返回的类型
     */
    private <T> T transform(Supplier<T> t) {
        try {
            return t.get();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            throw new MyException("the factory not exit");
        }
    }

    /**
     * 注册新转换器
     * @param rule 规则
     * @param converter 规则转换器
     */
    public synchronized void register(byte rule, StreamConversionFactory converter) {
        int index = getRuleIndex(rule);
        while (index > conversionFactories.length) {
            grow();
        }
        conversionFactories[rule] = converter;
    }

    /**
     * 扩容数组
     */
    private void grow() {
        StreamConversionFactory[] newFactory = new StreamConversionFactory[conversionFactories.length << 2];
        System.arraycopy(conversionFactories, 0, newFactory, 0, conversionFactories.length);
        conversionFactories = newFactory;
    }

    private int getRuleIndex(byte rule) {
        return rule;
    }


}
