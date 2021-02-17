package m.wysocki.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.annotation.Resource;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    freemarker.template.Configuration freeMakerConfiguration(){
        return new freemarker.template.Configuration();
    }

    @Resource(name = "myRegisterDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN","MANAGER");
        auth.inMemoryAuthentication().withUser("manager").password("{noop}manager").roles("MANAGER");
        auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http.authorizeRequests()
                .antMatchers("/appUsers*").access("hasRole('ADMIN')")
                .antMatchers("/registerRole*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/listofusers*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/exampleTwo*").access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/exampleThree*").access("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .and().formLogin().loginPage("/login").permitAll() // with custom login page
                .usernameParameter("login").passwordParameter("password")
                .failureForwardUrl("/login.html?error")
                .and().logout().logoutSuccessUrl("/login.html?logout")
                .and().exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
