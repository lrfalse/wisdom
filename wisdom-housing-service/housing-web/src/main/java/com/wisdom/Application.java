package com.wisdom;

import com.wisdom.framework.database.conf.DynamicDataSourceRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tk.mybatis.spring.annotation.MapperScan;

@EnableWebMvc
@Import({DynamicDataSourceRegister.class})
@SpringBootApplication
@MapperScan(basePackages = "com.wisdom.mapper")
public class Application  extends WebMvcConfigurerAdapter implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args){
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.run(args);
    }

    public void run(String... strings) throws Exception {
        logger.info("服务启动完成!");
    }
}
