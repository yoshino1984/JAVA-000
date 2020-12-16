package io.kimmking.rpcfx.demo.provider;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * @author wangxin
 * 2020/12/16 下午1:00
 * @since
 **/
@Configuration
public class HttpMessageConverterConfig {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializerFeature[] serializerFeatures = new SerializerFeature[] {
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteClassName
        };
        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastConverter);
    }

}
