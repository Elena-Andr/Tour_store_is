package ru.innopolis.tourstore.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.innopolis.tourstore.entity.UserEntity;
import ru.innopolis.tourstore.exception.UserDaoException;
import ru.innopolis.tourstore.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {

            String username = authentication.getName();
            String password = (String) authentication.getCredentials();
            UserEntity user = userService.getEntityByName(username);

            if(user != null){

                String encodedPassword = passwordEncoder.encode(password);

                if(encodedPassword.equals(user.getPassword())) {
                    final List<GrantedAuthority> grantedAuths = new ArrayList<>();
                    grantedAuths.add(new SimpleGrantedAuthority(user.getRole()));
                    return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
                }
            }

        }catch (UserDaoException e) {
            throw new AuthenticationServiceException("Exception was caused by UserDao " + e.getMessage());
        }

        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
