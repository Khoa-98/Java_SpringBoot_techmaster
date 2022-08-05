package vn.techmaster.demosercurity.sercurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("khoa").password(passwordEncoder().encode("111")).roles("USER")
                .and()
                .withUser("anna").password(passwordEncoder().encode("222")).roles("USER", "EDITOR")
                .and()
                .withUser("John").password(passwordEncoder().encode("333")).roles("USER", "ADMIN", "EDITOR");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/profile").hasRole("USER")
                .antMatchers("/admin/blog").hasAnyRole("EDITOR", "ADMIN")
                .antMatchers("/admin/user").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login-custom")
                    .usernameParameter("name")
                    .passwordParameter("pass")
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                .and()
                    .httpBasic();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
       web.ignoring()
               .antMatchers("/js/**", "/css/**");

    }
}
