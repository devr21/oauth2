package learn.spring.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
@ComponentScan("learn.spring.authorization")
public class Oauth2Config extends AuthorizationServerConfigurerAdapter{
	
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients.inMemory()
			.withClient("oauth-account")
			.authorizedGrantTypes("client_credentials","password")
			.scopes("resource-server-read","resource-server-write")
			.autoApprove(true)
			.authorities("ROLE_RS_READ","ROLE_RS_WRITE");
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private Environment env;
	
	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(jwtTokenEnhancer());
	}
	
	public void configure(AuthorizationServerSecurityConfigurer security) {
		security.tokenKeyAccess("permitAll()")
			.checkTokenAccess("isAuthenticated()");
	}
	
	@Bean
	protected JwtAccessTokenConverter jwtTokenEnhancer() {
		String pwd = env.getProperty("keystore.password");
		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt.jks"), pwd.toCharArray());
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"));
		return converter;
	}
	
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.authenticationManager(authenticationManager)
					.tokenStore(tokenStore())
					.tokenEnhancer(jwtTokenEnhancer());
	}
	
}
