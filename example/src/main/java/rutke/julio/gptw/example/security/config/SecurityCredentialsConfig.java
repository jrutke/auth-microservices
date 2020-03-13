package rutke.julio.gptw.example.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import rutke.julio.gptw.core.property.JwtConfiguration;
import rutke.julio.gptw.security.config.SecurityTokenConfig;
import rutke.julio.gptw.security.filter.JwtTokenAuthorizationFilter;
import rutke.julio.gptw.security.token.converter.TokenConverter;

@EnableWebSecurity
public class SecurityCredentialsConfig extends SecurityTokenConfig {
    private final TokenConverter tokenConverter;

    public SecurityCredentialsConfig(JwtConfiguration jwtConfiguration, TokenConverter tokenConverter) {
        super(jwtConfiguration);
        this.tokenConverter = tokenConverter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(new JwtTokenAuthorizationFilter(jwtConfiguration, tokenConverter),
                UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

}
