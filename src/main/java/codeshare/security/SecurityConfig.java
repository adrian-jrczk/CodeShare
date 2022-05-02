package codeshare.security;

import codeshare.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Order(1)
    @Configuration
    public static class ApiConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/*")
                    .csrf()
                        .disable()
                    .authorizeRequests()
                        .antMatchers("/api/register")
                            .permitAll()
                        .anyRequest()
                            .authenticated()
                        .and()
                    .httpBasic();
        }
    }

    @Order(2)
    @Configuration
    public static class ViewConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/", "/register", "/css/*", "/js/*")
                            .permitAll()
                        .antMatchers(HttpMethod.POST, "/register")
                            .permitAll()
                        .anyRequest()
                            .authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login")
                            .permitAll()
                        .usernameParameter("name")
                        .defaultSuccessUrl("/my-code")
                        .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/");
        }
    }
}
