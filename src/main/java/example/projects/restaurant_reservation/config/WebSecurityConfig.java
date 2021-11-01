package example.projects.restaurant_reservation.config;

import example.projects.restaurant_reservation.service.UserDetailServiceImplementation;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserDetailServiceImplementation userDetailServiceImplementation;

    public WebSecurityConfig(BCryptPasswordEncoder passwordEncoder, UserDetailServiceImplementation userDetailServiceImplementation) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailServiceImplementation = userDetailServiceImplementation;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/")
                .permitAll()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServiceImplementation).passwordEncoder(passwordEncoder);
    }
}
