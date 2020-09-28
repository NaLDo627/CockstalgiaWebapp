package net.hkpark.cockstalgia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class CockstalgiaCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CockstalgiaCoreApplication.class, args);
	}

}