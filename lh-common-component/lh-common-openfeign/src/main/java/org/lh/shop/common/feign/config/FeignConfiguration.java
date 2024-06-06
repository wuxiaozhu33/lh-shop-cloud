package org.lh.shop.common.feign.config;

import cn.hutool.core.date.DatePattern;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.lh.shop.common.core.constant.Constants;
import org.lh.shop.common.core.domin.R;
import org.lh.shop.common.core.exception.ServiceException;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Feign调用解析器
 * @author wuYf
 * @date 2024/06/06
 */
@AutoConfiguration
public class FeignConfiguration {

    @Bean
    public Decoder feignDecoder() {
        return (response, type) -> {
            if (response.body() == null) {
                throw new DecodeException(response.status(), "没有返回有效的数据", response.request());
            }
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            //对结果进行转换
            if (type instanceof R) {
                R info = JSON.parseObject(bodyStr,R.class);
                //如果返回错误，且为内部错误，则直接抛出异常;直接返回了消息
                if (info.getCode() == Constants.FAIL) {
                    throw new ServiceException(info.getMsg());
                }
                return json2obj(JSON.toJSONString(info.getData()), type);
            }else {
                return json2obj(bodyStr, type);
            }
        };
    }

    private static <T> T json2obj(String jsonStr, Type targetType) {
        try {
            JsonMapper objectMapper = new JsonMapper();

            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
            objectMapper.registerModule(javaTimeModule);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
            JavaType javaType = TypeFactory.defaultInstance().constructType(targetType);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            throw new IllegalArgumentException("将JSON转换为对象时发生错误:" + jsonStr, e);
        }
    }
}