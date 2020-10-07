package com.oi.daas.reconciliationcommand.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "recon")
@Getter
@Setter
public class AppProperties {
    private String dataReconciliationHost;
}
