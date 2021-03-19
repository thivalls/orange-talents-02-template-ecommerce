package br.com.zup.mercadolivre.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
//@Profile()
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/*/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST, "/categories").permitAll()
                .antMatchers(HttpMethod.POST, "/products").permitAll()
                .antMatchers(HttpMethod.POST, "/products/{id}/images").permitAll()
                .antMatchers(HttpMethod.POST, "/products/{id}/opinion").permitAll()
                .antMatchers(HttpMethod.POST, "/products/{id}/question").permitAll()
                .antMatchers(HttpMethod.POST, "/orders").permitAll()
                .antMatchers(HttpMethod.POST, "/return-paypal/{idTransaction}").permitAll()
                .antMatchers(HttpMethod.POST, "/return-pagseguro/{idTransaction}").permitAll()
                .antMatchers(HttpMethod.POST, "/external/services/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}
