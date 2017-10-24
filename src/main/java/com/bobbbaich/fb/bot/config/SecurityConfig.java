package com.bobbbaich.fb.bot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.social.security.SocialAuthenticationProvider;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    private SocialAuthenticationProvider socialAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers().disable()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/failure", "/signin/**", "/connect/**", "/signup/**").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .apply(new SpringSocialConfigurer()
                        .postLoginUrl("/")
                        .alwaysUsePostLoginUrl(true))
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler())
                .deleteCookies("JSESSIONID")
                .permitAll();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(socialAuthenticationProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Autowired
    public void setSocialAuthenticationProvider(SocialAuthenticationProvider socialAuthenticationProvider) {
        this.socialAuthenticationProvider = socialAuthenticationProvider;
    }
}
