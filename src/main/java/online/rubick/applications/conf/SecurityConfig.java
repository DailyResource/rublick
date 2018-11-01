package online.rubick.applications.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import online.rubick.applications.security.web.CrucianAuthenticationEntryPoint;
import online.rubick.applications.security.web.CrucianAuthenticationFilter;
import online.rubick.applications.security.web.CrucianAuthenticationProvider;
import online.rubick.applications.security.web.CrucianAuthenticationSuccessHandler;
import online.rubick.applications.security.web.CrucianLogoutSuccessHandler;
import online.rubick.applications.security.web.SessionRegistryClass;

/**
 * Spring Security 配置类
 * 
 * @author 张峻峰
 * @Date 2017年11月14日
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CrucianAuthenticationProvider provider;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private SessionRegistryClass sessionRegistry;
	
	@Autowired
	private CrucianAuthenticationSuccessHandler authSuccessHandler;
	
	@Autowired
	private CrucianLogoutSuccessHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authenticationProvider(provider);
		http.httpBasic().authenticationEntryPoint(createEntryPoint());
		http.addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class);
		http.sessionManagement().invalidSessionUrl("/APIs/invalidSession");
		http.csrf().disable();
		// 一个用户只能有一个session
		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/APIs/invalidSession");

		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutHandler).invalidateHttpSession(true).deleteCookies("JSESSIONID");
		

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
				.authorizeRequests();
		/**
		 * 过滤掉swagger需要的内容
		 */
		registry.antMatchers("/APIs/swagger-ui.html",
		        "/APIs/v2/api-docs",
		        "/APIs/swagger-resources/**",
				"/APIs/userOperation/resetPasswords",
				"/APIs/verifycode").permitAll();

		registry.anyRequest().authenticated().and().formLogin().loginPage("/APIs/login.html").permitAll().and().logout()
				.permitAll();
	}

	private CrucianAuthenticationFilter getFilter() {
		// 这里new 过滤器的时候需要sessionRegistry
		CrucianAuthenticationFilter filter = new CrucianAuthenticationFilter(sessionRegistry);
		filter.setAuthenticationManager(authManager);
		authSuccessHandler.setDefaultTargetUrl("/APIs/index.html");
		filter.setAuthenticationSuccessHandler(authSuccessHandler);
		return filter;
	}

	// Security 入口
	public CrucianAuthenticationEntryPoint createEntryPoint() {
		return new CrucianAuthenticationEntryPoint("/APIs/login.html");
	}

}
