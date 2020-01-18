/**
 *	@Date:2019-09-17
 */
package com.example.config;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @param <V>
 * @Author ZhangChunjie
 *
 *         DruidConfig.java
 *
 *         time:2019-09-17
 */
@Configuration
public class DruidConfig {
	@Value("${druid.username}")
	String username;
	
	@Value("${druid.password}")
	String password;
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource druid() {
		DruidDataSource dataSource = new DruidDataSource();
		//设置初始大小，最小，最大连接数，最大等待时间
		dataSource.setInitialSize(10);
		dataSource.setMinIdle(10);
		dataSource.setMaxActive(50);
		dataSource.setMaxWait(10*1000L);
		//设置心跳检测，检测需要关闭的链接  
		dataSource.setTimeBetweenEvictionRunsMillis(10*1000L);
		try {
			// 配置拦截器
			dataSource.setFilters("stat,wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
		dataSource.setUseGlobalDataSourceStat(true);
		Properties properties = new Properties();
		properties.put("druid.stat.mergeSql", true);
		properties.put("druid.stat.slowSqlMillis", 500);
		dataSource.setConnectProperties(properties);
		return dataSource;
	}

	@Bean
	public ServletRegistrationBean<StatViewServlet> statViewServlet() { 
		ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<StatViewServlet>(new StatViewServlet(), "/druid/*");
		Map<String, String> initParameters = new HashMap<String, String>();
		initParameters.put("loginUsername", username);
		initParameters.put("loginPassword", password);
		servletRegistrationBean.setInitParameters(initParameters);
		return servletRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<WebStatFilter> webStatFilter(){
		FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
			filterRegistrationBean.setFilter(new WebStatFilter());
			Map<String, String> initParameters = new HashMap<String, String>();
			initParameters.put("exclusions", "*.js,*.css,/druid/*");
			filterRegistrationBean.setInitParameters(initParameters);
			filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
			return filterRegistrationBean;

	}
}
