package com.laioffer.onlineOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;

// 这个类会被 Filter 自动来执行

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // Dependency injection_Field Injection
    @Autowired
    private DataSource dataSource;

    // Authentication manager
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Login fail 怎么办
        http
                .csrf().disable()   // csrf: 跨域请求伪造(一种攻击手段)；本项目没有实现对 csrf 攻击的防范
                .formLogin()        // login 的形式是 form（表单），传递用户名和密码等信息
                .failureForwardUrl("/login?error=true");    // 登录失败就跳到这个网址

        // Authorize login request
        http
                .authorizeRequests()
                // 确认用户登录了才能访问以下页面； ROlE_USER 要和 CustomerDao 里保持一致
                .antMatchers("/order/*","/cart","/checkout").hasAuthority("ROLE_USER")
                // 其他操作不需要 login 也能做
                .anyRequest().permitAll();
    }

    // http builder configurations for authorize requests and from login
    // This method tells Security where to find/get the login data
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM customers WHERE email=?")   // From class Customer
                .authoritiesByUsernameQuery("SELECT email, authorities FROM authorities WHERE email=?");    // From class Authorities
    }

    @SuppressWarnings("deprecation")
    @Bean
    // 本项目是明文的 password，没有加密；by default 是有加密的
    public static NoOpPasswordEncoder passwordEncoder() {
        return(NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
