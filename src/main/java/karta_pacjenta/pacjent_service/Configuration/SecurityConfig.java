package karta_pacjenta.pacjent_service.Configuration;

import karta_pacjenta.pacjent_service.Models.Enums.UserRoles;
import karta_pacjenta.pacjent_service.Services.MyAppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyAppUserDetailsService myAppUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/test").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .antMatchers("/api/**").hasAnyAuthority(UserRoles.PATIENT.getRole(), UserRoles.ADMIN.getRole(), UserRoles.DOCTOR.getRole())
                .anyRequest().hasAnyAuthority(UserRoles.ADMIN.getRole())
                .and()
                .csrf().disable();

        http.cors();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myAppUserDetailsService);
    }
}