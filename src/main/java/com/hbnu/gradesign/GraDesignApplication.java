package com.hbnu.gradesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@SpringBootApplication
@MapperScan(basePackages = "com.hbnu.gradesign.dao")
public class GraDesignApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GraDesignApplication.class);
	}

	/**
	 * JackJSON配置
	 * 1.需要先定义一个convert转换消息的对象；
	 * 2.添加fastjson的配置信息，比如是否要格式化返回的json数据
	 * 3.在convert中添加配置信息
	 * 4.将convert添加到converters中
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastJsonHttpMessageConverters() {
		//创建转换对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
		//创建配置文件对象,添加fastJson的配置信息
		FastJsonConfig fastJsonConfig = new FastJsonConfig();
		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
		///解决中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		fastConverter.setSupportedMediaTypes(fastMediaTypes);
		//在convert中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);
		//将convert添加到converters中
		HttpMessageConverter<?> converter = fastConverter;
		return new HttpMessageConverters(converter);
	}

	/**
	 * PagerHelper配置
	 * @return
	 */
	@Bean
	public PageHelper pageHelper(){
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum","true");
		properties.setProperty("rowBoundsWithCount","true");
		properties.setProperty("reasonable","true");
		properties.setProperty("dialect","mysql");
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	public static void main(String[] args) {
		SpringApplication.run(GraDesignApplication.class, args);
	}

}
