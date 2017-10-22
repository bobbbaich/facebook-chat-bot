package com.bobbbaich.fb.bot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.*;

import javax.sql.DataSource;

@Configuration
@EnableSocial
@EnableWebSecurity
public class SocialConfig extends SocialConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SocialConfig.class);

    private static final String LOGIN_FORM_URL = "/signin";

    private DataSource dataSource;
    private ConnectionSignUp connectionSignUp;

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
        return jdbcUsersConnectionRepository;
    }

    @Bean
    public SocialAuthenticationProvider socialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository,
                                                                     SocialUserDetailsService userDetailsService) {
        LOG.debug("SocialAuthenticationProvider was initialised.");
        return new SocialAuthenticationProvider(usersConnectionRepository, userDetailsService);
    }

    @Bean
    public LoginUrlAuthenticationEntryPoint socialAuthenticationEntryPoint() {
        return new LoginUrlAuthenticationEntryPoint(LOGIN_FORM_URL);
    }

    @Bean
    //TODO: UsersConnectionRepository inject as a class property
    public SocialAuthenticationFilter socialAuthenticationFilter(UsersConnectionRepository connectionRepository,
                                                                 ConnectionFactoryLocator connectionFactoryLocator,
                                                                 AuthenticationManager authenticationManager) {
        SocialAuthenticationFilter filter = new SocialAuthenticationFilter(authenticationManager, getUserIdSource(),
                connectionRepository, (SocialAuthenticationServiceLocator) connectionFactoryLocator);
        filter.setFilterProcessesUrl(LOGIN_FORM_URL);
        filter.setSignupUrl("/signup");
        filter.setConnectionAddedRedirectUrl("/test");
        filter.setPostLoginUrl("/test");
        return filter;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setConnectionSignUp(ConnectionSignUp connectionSignUp) {
        this.connectionSignUp = connectionSignUp;
    }
}
