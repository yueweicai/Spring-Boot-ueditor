package com.orilore.config;

import javax.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.CorsFilter;
/**
 * SpringBoot 跨域配置器
 * @author yueweicai
 */
@Configuration
public class CorsConfig {
	/**
	 * 构建配置对象
	 * @return Configuration
	 */
	private CorsConfiguration buildConfig() {
		CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); 
        config.addAllowedHeader("*"); 
        config.addAllowedMethod("*"); 
        return config;
    }
	
	/**
	 * 配置跨域过滤器
	 * @return filter
	 */
    @Bean
    public CorsFilter corsFilter() {
    	//存储request与跨域配置信息的容器
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); 
        return new CorsFilter(source);
    }
    /**
     * 处理中文字符编码问题
     * @return Filter
     */
    @Bean
    public Filter characterEncodingFilter() {
    	CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");  
        filter.setForceEncoding(true);  
        return filter;  
    }  
}