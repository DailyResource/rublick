package online.rubick.applications.conf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import online.rubick.applications.interceptor.CORSInterceptor;
import online.rubick.applications.interceptor.CheckClassInterceptor;
import online.rubick.applications.security.annotation.AccountArgumentResolver;

/**
 * 配置 Spring MVC 扩展插件
 * 
 * @author 张峻峰
 * @Date 2017年11月15日
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	// 抓取session中的用户信息
	@Autowired
	private AccountArgumentResolver sessionResolver;

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);
		argumentResolvers.add(sessionResolver);
	}
	
	@Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getMyInterceptor(){
        return new CheckClassInterceptor();
    }
	
	@Bean   //把我们的拦截器注入为bean
    public HandlerInterceptor getCORSInterceptor(){
        return new CORSInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则, 这里假设拦截 /url 后面的全部链接
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/**");
        //registry.addInterceptor(getCORSInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

	

}
