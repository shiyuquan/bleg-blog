package syq.bleg.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import syq.bleg.io.DefaultStreamConverter;
import syq.bleg.io.StreamConverter;

/**
 * @author shiyuquan
 * Create Time: 2019/7/26 16:50
 */
@Configuration
public class EncryptionStreamConfig {

    /**
     * 加载一个流转换器到上下文
     */
    @Bean
    @ConditionalOnMissingBean
    public StreamConverter streamConverter() {
        return new DefaultStreamConverter();
    }

}
