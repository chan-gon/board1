package com.changon.board.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@EnableTransactionManagement
@Configuration // 스프링은 @Configuration이 지정된 클래스를 자바 기반의 설정 파일로 인식한다.
@PropertySource("classpath:/application.properties") // 해당 클래스에서 참조할 properties 파일 위치 지정
public class DBConfiguration {
	
	@Autowired // Bean으로 등록된 인스턴스를 클래스에 주입하는데 사용
	private ApplicationContext applicationContext;
	
	/*
	 ConfigurationProperties는 prefix로 지정된 값으로 시작하는 설정을 모두 읽어들여 해당 메서드에 매핑(바인딩)한다.
	 */
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() { // 커넥션 풀 라이브러리 중 하나인 Hikari CP 생성
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() { // 커넥션 풀을 지원하기 위한 인터페이스
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml"));
		factoryBean.setTypeAliasesPackage("com.changon.board.*");
		factoryBean.setConfiguration(mybatisConfig());
		return factoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		return new org.apache.ibatis.session.Configuration();
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
