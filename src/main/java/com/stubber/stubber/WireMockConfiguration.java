package com.stubber.stubber;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WireMockConfiguration {

    @Bean(destroyMethod = "stop")
    public WireMockServer wireMockServer() {
        WireMockServer wireMockServer = new WireMockServer(3002); // Use the actual WireMock server port
        wireMockServer.start();
        return wireMockServer;
    }
}