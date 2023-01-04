package Youcode.project.security;


import Youcode.project.filter.CustomAuthenticationFilter;
import Youcode.project.filter.CutomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired
 UserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter=new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/visionarycrofting/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/visionarycrofting/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET,"/visionarycrofting/**").hasAnyAuthority("STOCK_MANAGER");
        http.authorizeRequests().antMatchers(GET,"/visionarycrofting/Client/**").hasAnyAuthority("STOCK_MANAGER");
        http.authorizeRequests().antMatchers(POST,"/visionarycrofting/Client/**").hasAnyAuthority("CLIENT");
        http.authorizeRequests().antMatchers(POST,"/visionarycrofting/Client/passer_commande").hasAnyAuthority("CLIENT");
        http.authorizeRequests().antMatchers(POST,"/visionarycrofting/Client/addClient").hasAnyAuthority("CLIENT");
        http.authorizeRequests().antMatchers(PUT,"/visionarycrofting/Provider/validateInvoice/**").hasAnyAuthority("FOURNISSEUR");
        http.authorizeRequests().antMatchers(POST,"/visionarycrofting/invoice/insert/**").hasAnyAuthority("STOCK_MANAGER");



        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CutomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
      return   super.authenticationManagerBean();
    }
}
