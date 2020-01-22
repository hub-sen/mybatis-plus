package org.mp.high;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author shishi
 */
@SpringBootApplication
@MapperScan("org.mp.high.dao")
public class HighApplication {

	public static void main(String[] args) {
		SpringApplication.run(HighApplication.class, args);
	}

}
