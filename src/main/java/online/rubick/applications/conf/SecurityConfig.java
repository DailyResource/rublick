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
		http.sessionManagement().invalidSessionUrl("/invalidSession");
		http.csrf().disable();
		// 一个用户只能有一个session
		http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/invalidSession");

		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutHandler).invalidateHttpSession(true).deleteCookies("JSESSIONID");
		

		ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http
				.authorizeRequests();
		/**
		 * 过滤掉swagger需要的内容
		 */
		registry.antMatchers("/swagger-ui.html",
		        "/v2/api-docs",
		        "/swagger-resources/**",
				"/userOperation/resetPasswords",
				"/verifycode").permitAll();

		registry.anyRequest().authenticated().and().formLogin().loginPage("/login.html").permitAll().and().logout()
				.permitAll();
	}

	private CrucianAuthenticationFilter getFilter() {
		// 这里new 过滤器的时候需要sessionRegistry
		CrucianAuthenticationFilter filter = new CrucianAuthenticationFilter(sessionRegistry);
		filter.setAuthenticationManager(authManager);
		authSuccessHandler.setDefaultTargetUrl("/index.html");
		filter.setAuthenticationSuccessHandler(authSuccessHandler);
		return filter;
	}

	// Security 入口
	public CrucianAuthenticationEntryPoint createEntryPoint() {
		return new CrucianAuthenticationEntryPoint("/login.html");
	}

}
