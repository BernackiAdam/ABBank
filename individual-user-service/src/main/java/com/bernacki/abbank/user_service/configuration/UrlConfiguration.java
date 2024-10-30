package com.bernacki.abbank.user_service.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("service-url")
public class UrlConfiguration {
    private String gatewayUrl;
    private String accListEndpoint;
    private String accAddEndpoint;
    private String userEndpoint;
}
