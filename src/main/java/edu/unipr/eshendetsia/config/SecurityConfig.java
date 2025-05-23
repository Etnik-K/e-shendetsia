package edu.unipr.eshendetsia.config;

import edu.unipr.eshendetsia.http.request.filter.AuthFilter;
import edu.unipr.eshendetsia.http.request.filter.LoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public FilterRegistrationBean<LoggingFilter> loggingFilterRegistration(LoggingFilter loggingFilter) {
        FilterRegistrationBean<LoggingFilter> loggingFilterRegistrationBean = new FilterRegistrationBean<>(loggingFilter);

        // bone me run i pari, logging filteri me run i pari si filter, mas logging filterit
        loggingFilterRegistrationBean.setOrder(1);
        return loggingFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterRegistration(AuthFilter authFilter) {
        FilterRegistrationBean<AuthFilter> authFilterRegistrationBean = new FilterRegistrationBean<>(authFilter);

        //ky i dyti
        authFilterRegistrationBean.setOrder(2);
        return authFilterRegistrationBean;
    }

}
