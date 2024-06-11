package org.lh.shop.common.feign.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.json.JSONUtil;
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
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@AutoConfiguration
public class FeignConfiguration {
    @Bean
    public Decoder feignDecoder() {
        return (response, type) -> {
            if (response.body() == null) {
                throw new DecodeException(response.status(), "没有返回有效的数据", response.request());
            }
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            log.info("open feign url:{}",response.request().url());
            log.info("headers:{}",response.request().headers());
            //对结果进行转换
            R info = JSON.parseObject(bodyStr,R.class);
            //如果返回错误，且为内部错误，则直接抛出异常;直接返回了消息
            if (info.getCode()!=0 && R.isError(info)) {
                throw new ServiceException(info.getMsg());
            }
            if (info.getData()==null) {
                info.setData(bodyStr);
            }
            String data = info.getData().toString();
            boolean typeJson = JSONUtil.isTypeJSON(data);
            return json2obj(typeJson?data:JSON.toJSONString(data), type);
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
            throw new IllegalArgumentException("返回结果转换为"+targetType+"对象时发生错误:" + jsonStr, e);
        }
    }
}