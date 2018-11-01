package online.rubick.applications.conf;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import online.rubick.applications.util.PageSortInterceptor;

@Configuration
public class MybatisConfiger {
	@Bean
	SqlSessionFactory createSqlSessionFactory(@Autowired DataSource dataSource, @Autowired ApplicationContext ctx)
			throws Exception {
		SqlSessionFactoryBean b = new SqlSessionFactoryBean();
		b.setDataSource(dataSource);
		b.setTypeAliasesPackage("online.rubick.applications.entity");
		PathMatchingResourcePatternResolver r = new PathMatchingResourcePatternResolver();
		b.setMapperLocations(r.getResources("classpath*:/**/*Mapper.xml"));
		b.setTypeHandlersPackage("online.rubick.applications.dao.typehandler");

		// 分页插件
		Interceptor pageSort = new PageSortInterceptor();
		b.setPlugins(new Interceptor[] { pageSort });

		return b.getObject();
	}

	@Bean
	MapperScannerConfigurer createMapperScannerConfigurer() {
		MapperScannerConfigurer cfg = new MapperScannerConfigurer();
		cfg.setBasePackage("online.rubick.applications.*");
		cfg.setAnnotationClass(Repository.class);
		return cfg;
	}
}
