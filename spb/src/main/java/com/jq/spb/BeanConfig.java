package com.jq.spb;

import com.jq.spb.bean.App;
import com.jq.spb.convert.StringToDate;
import com.jq.spb.convert.StringToDateCustomEditorConfigurer;
import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.beans.support.ResourceEditorRegistrar;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import java.beans.PropertyEditor;
import java.util.*;

/**
 * @author Jiangqing
 * @version 1.0
 * @since 2019-08-13 16:30
 */
@Configuration
//@EnableConfigurationProperties(App.class)
public class BeanConfig {

    @ConfigurationProperties("app")
    @Bean
    public App app(){
        return new App();
    }

   /* @Bean
    public ConversionService conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new StringToDate());
       // converters.add(new IntegerToDateConverter());
        bean.setConverters(converters);
        bean.afterPropertiesSet();
        return bean.getObject();
    }*/

   /* @Bean
    public CustomEditorConfigurer customEditorConfigurer() {
        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
        Map<Class<?>, Class<? extends PropertyEditor>> customEditors = new HashMap<>();
        customEditors.put(Date.class, StringToDateCustomEditorConfigurer.class);
        customEditorConfigurer.setCustomEditors(customEditors);
        return  customEditorConfigurer;
    }*/

}
