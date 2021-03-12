package fr.campus.academy.backenddev.examen.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class CustomWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("visitor").password(this.passwordEncoder.encode("visitor")).roles("VISITOR")
                .and()
                .withUser("organisator").password(this.passwordEncoder.encode("organisator")).roles("ORGANISATOR", "VISITOR");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin().and()
                .logout().deleteCookies("JSESSIONID").invalidateHttpSession(true).and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.PUT).hasRole("ORGANISATOR")
                .antMatchers(HttpMethod.POST).hasRole("ORGANISATOR")
                .antMatchers(HttpMethod.DELETE).hasRole("ORGANISATOR")
                .antMatchers(HttpMethod.GET).hasAnyRole("ORGANISATOR", "VISITOR")
                .antMatchers("/**").hasAnyRole("VISITOR", "ORGANISATOR")
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
