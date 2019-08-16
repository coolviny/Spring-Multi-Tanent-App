package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import com.filter.ProtectedApiFilter;


/**
 * All application filter configuration.
 * @author vishal
 *
 */
@Configuration
public class FilterConfig {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FilterConfig.class);
	
	@Autowired
	private ProtectedApiFilter protectedApiFilter;
 
    @Bean
	public ProtectedApiFilter getProtectedApiFilter() {
		return new ProtectedApiFilter();
	}

    /**
     * @return FilterRegistrationBean : Api level filter for protected api request. Ex. /api/protected/*
     */
    @Bean
    public FilterRegistrationBean protectedApiFilter(){
    	FilterRegistrationBean filter = new FilterRegistrationBean();
    	filter.setOrder(4);
    	filter.addUrlPatterns("/api/protected/*");
    	filter.setFilter(protectedApiFilter);
    	return filter;
    }

	/**
	 * Return MessageSource to load message from property file.
	 * @return
	 */
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("properties/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
    }
}
