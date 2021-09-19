package com.example.openfeign.config;

import feign.Feign;
import feign.Retryer;
import feign.hc5.ApacheHttp5Client;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@Slf4j
public class ApacheHttp5FeignSslClientConfig {

    @Bean
    public Feign.Builder feignBuilder(
        @Value("${client.api.ssl.protocol}") String protocol,
        @Value("${client.api.ssl.key-store-type}") String keyStoreType,
        @Value("${client.api.ssl.key-store}") String keyStore,
        @Value("${client.api.ssl.key-store-password}") String keyStorePassword,
        @Value("${client.api.ssl.key-password}") String keyPassword,
        @Value("${client.api.ssl.trust-store}") String trustStore,
        @Value("${client.api.ssl.trust-store-password}") String trustStorePassword,
        @Value("${client.api.ssl.proxy-host}") String proxyHost,
        @Value("${client.api.ssl.proxy-port}") String proxyPort

    ) {
        SSLContext sslContext = getSSLContext(protocol, keyStoreType, keyStore, keyStorePassword, keyPassword, trustStore, trustStorePassword);
        SSLConnectionSocketFactory sslConnectionSocketFactory = SSLConnectionSocketFactoryBuilder.create().setSslContext(sslContext).build();
        HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory).build();
        return Feign.builder()
            .retryer(Retryer.NEVER_RETRY)
            .client(new ApacheHttp5Client(HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setProxy(StringUtils.isNotEmpty(proxyHost) ? new HttpHost(proxyHost, proxyPort) : null)
                .build()));
    }

    private SSLContext getSSLContext(String protocol, String keyStoreType, String keyStore, String keyStorePassword, String keyPassword, String trustStore, String trustStorePassword) {
        try {
            TrustStrategy acceptingTrustStrategy = (chain, authType) -> true;
            return SSLContexts.custom()
                .setProtocol(protocol)
                .setKeyStoreType(keyStoreType)
                .loadKeyMaterial(ResourceUtils.getFile(keyStore), keyStorePassword.toCharArray(), keyPassword.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile(trustStore), trustStorePassword.toCharArray(), acceptingTrustStrategy)
                .build();
        } catch (IOException | UnrecoverableKeyException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error("Error while building SSLContext for ApacheHttp5FeignSslClient", e);
            throw new ExceptionInInitializerError("Error while building SSLContext for ApacheHttp5FeignSslClient");
        }
    }
}
