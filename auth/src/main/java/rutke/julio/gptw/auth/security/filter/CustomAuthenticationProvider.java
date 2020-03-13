package rutke.julio.gptw.auth.security.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import rutke.julio.gptw.core.model.ApplicationUser;
import rutke.julio.gptw.core.repository.ApplicationUserRepository;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        String verificationCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getVerificationCode() ;
        ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
        if((applicationUser == null) || !applicationUser.getPassword().equals(password) || !applicationUser.getAuthemail().equals(verificationCode)){
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(applicationUser, password, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
