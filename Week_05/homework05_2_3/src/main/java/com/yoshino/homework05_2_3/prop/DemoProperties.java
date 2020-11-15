package com.yoshino.homework05_2_3.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@Data
@ConfigurationProperties(prefix = "yoshino")
public class DemoProperties {

    private Properties props = new Properties();

}
