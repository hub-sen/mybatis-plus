package org.mp.start.configuration;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/21 15:36
 * </pre>
 */
@Configuration
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor(){
		return new PaginationInterceptor();
	}
}
