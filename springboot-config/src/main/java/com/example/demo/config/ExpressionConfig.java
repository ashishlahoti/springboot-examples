package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@Data
@JsonIgnoreProperties({"$$beanFactory"})
public class ExpressionConfig {

    @Value("#{new java.net.URI('${bootstrap.url}').getScheme()}")
    private String scheme;

    @Value("#{new java.net.URI('${bootstrap.url}').getHost()}")
    private String host;

    @Value("#{new java.net.URI('${bootstrap.url}').getPort()}")
    private Integer port;

    @Value("#{'Java, JavaScript, Python, Ruby'.split(',')}")
    private List<String> languages;

    @Value("#{'Java, JavaScript, Python, Ruby'.split(',')[0]}")
    private String firstLanguage;

    @Value("#{((1 + 2^3 - 4) * (5 % 6)) / 7 }") // 3.0
    private Double arithmeticOperation;

    @Value("#{((1 + 2^3 - 4) * (5 mod 6)) div 7 }") // 3.0
    private Double anotherArithmeticOperation;

    @Value("#{'Hello ' + 'World'}") // "Hello World"
    private String concatString;

    // @Value("#{1 == 1}") true
    @Value("#{1 eq 1}") // true
    private boolean equal;

    //@Value("#{1 != 1}") // false
    @Value("#{1 ne 1}") // false
    private boolean notEqual;

    // @Value("#{1 < 1}") // false
    @Value("#{1 lt 1}") // false
    private boolean lessThan;

    //@Value("#{1 <= 1}") // true
    @Value("#{1 le 1}") // true
    private boolean lessThanOrEqual;

    //@Value("#{1 > 1}") // false
    @Value("#{1 gt 1}") // false
    private boolean greaterThan;

    //@Value("#{1 >= 1}") // true
    @Value("#{1 ge 1}") // true
    private boolean greaterThanOrEqual;

    //@Value("#{250 > 200 && 200 < 4000}") // true
    @Value("#{250 > 200 and 200 < 4000}") // true
    private boolean andOperation;

    //@Value("#{400 > 300 || 150 < 100}") // true
    @Value("#{400 > 300 or 150 < 100}") // true
    private boolean orOperation;

    //@Value("#{!true}") // false
    @Value("#{not true}") // false
    private boolean notOperation;

    @Value("#{2 > 1 ? 'a' : 'b'}") // "a"
    private String ternaryOperator;

    @Autowired
    private InlineConfig inlineConfig;

    @Value("#{inlineConfig.title != null ? inlineConfig.title : 'default'}")
    private String nullCheckUsingTernaryOperator;

    @Value("#{inlineConfig.title ?: 'default'}") // Will inject provided string if someProperty is null
    private String nullCheckUsingElvisOperator;

}
