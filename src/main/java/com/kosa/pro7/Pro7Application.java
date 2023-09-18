package com.kosa.pro7;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value={"com.kosa.pro7.mappers"})
public class Pro7Application {

	public static void main(String[] args) {
		SpringApplication.run(Pro7Application.class, args);
	}

//	@Bean
//    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//
//        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*.xml");
//        sessionFactory.setMapperLocations(res);
//
//        return sessionFactory.getObject();
//    }
	
}
