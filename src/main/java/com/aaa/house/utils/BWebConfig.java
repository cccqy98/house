//package com.aaa.house.utils;
//
//
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
//import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
//import org.springframework.boot.web.servlet.ServletComponentScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Configuration
//@ServletComponentScan // 支持自定义web过滤器和servlet
//class WebConfig implements WebMvcConfigurer {
//
///**
//	 *
//	 * @title: httpMessageConverters
//	 * @description: FastJson配置
//	 * @return
//     * 对传输的数据进行处理*/
//
//
//	@Bean
//	public HttpMessageConverters httpMessageConverters() {
////	    fastjson转换器
//		FastJsonHttpMessageConverter fjhmc = new FastJsonHttpMessageConverter();
////		设置编码方式
////		fjhmc.setCharset(FastJsonHttpMessageConverter.UTF8);
////		获取的数据
//		List<MediaType> types = new ArrayList<MediaType>();
////		数据使用的解析方式
//		types.add(MediaType.APPLICATION_JSON_UTF8);
////       默认支持的类型
//		fjhmc.setSupportedMediaTypes(types);
////		传输参数设置
//		fjhmc.setFeatures(SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteMapNullValue, SerializerFeature.QuoteFieldNames, SerializerFeature.PrettyFormat, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty);
////		页面返回数据
//		return new HttpMessageConverters(fjhmc);
//	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		// 设置允许跨域的路径
//		registry.addMapping("/**")
//				// 设置允许跨域请求的域名
//				.allowedOrigins("*").allowedHeaders("*")
//				// 是否允许证书 不再默认开启
//				.allowCredentials(true)
//				// 设置允许的方法
//				.allowedMethods("*");
//	}
//
//
//
//}
