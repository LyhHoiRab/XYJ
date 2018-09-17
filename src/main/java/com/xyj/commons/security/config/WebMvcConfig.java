package com.xyj.commons.security.config;

import com.xyj.commons.security.config.converter.WahHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport{

    @Override
    public void addInterceptors(InterceptorRegistry registry){

    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters){
        //自定义converter
        WahHttpMessageConverter wahConverter = new WahHttpMessageConverter();
        wahConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_HTML));

        //String converter
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

        converters.add(wahConverter);
        converters.add(stringConverter);
    }
}
