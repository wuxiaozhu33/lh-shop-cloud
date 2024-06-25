package org.lh.shop.pay.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 微信配置 Bean
 *
 * @author Javen
 */
@Data
@EqualsAndHashCode
@Component
@PropertySource("classpath:/wxpay_v3.properties")
@ConfigurationProperties(prefix = "v3")
public class WxPayV3Prop {
	private String appId;
	private String keyPath;
	private String publicKeyPath;
	private String certPath;
	private String certP12Path;
	private String platformCertPath;
	private String mchId;
	private String apiKey;
	private String apiKey3;
	private String domain;
}
