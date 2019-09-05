package org.thenx;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author May
 * <p>
 * 测试
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class LogMonitorApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LogMonitorApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 设置启动类,用于独立tomcat运行的入口
        return builder.sources(LogMonitorApplication.class);
    }

}
