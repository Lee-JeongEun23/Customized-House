package com.scoder.hs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.scoder.hs.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Override
	public void configure(WebSecurity web) {
		// static ���͸��� ���� ���� ����� ���� ���� ( = �׻���� )
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}
	
	/*
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/").permitAll()
        	.and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and();
    }
    */
	
	/*
	 HttpSecurity : HTTP ��û�� ���� �� ��� ������ ����
	 
	 */

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
	        // ������ ���� ����
				/*
				 * .antMatchers("/admin/**").hasRole("ADMIN")
				 * .antMatchers("/user/myinfo").hasRole("MEMBER")
				 */
	        
	        .antMatchers("/user/**")
	        .permitAll()
        .and() // �α��� ����
	        .formLogin()
	        .loginPage("/user/login")
	        .defaultSuccessUrl("/user/login/result")
	        .permitAll()
        .and() // �α׾ƿ� ����
	        .logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
	        .logoutSuccessUrl("/user/logout/result")
	        .invalidateHttpSession(true)
        .and()
	        // 403 ����ó�� �ڵ鸵
	        .exceptionHandling().accessDeniedPage("/user/denied");
    }
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }
	

}
