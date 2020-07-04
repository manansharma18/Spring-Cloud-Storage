package com.storage.cloud.service;

import com.storage.cloud.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    private final UserService userService;
    private final HashService hashService;

    public AuthenticationService(UserService userService, HashService hashService) {
        this.userService = userService;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getUser(username);
        if(user!=null){
            String encodedSalt = user.getSalt();
            String encodedPassword = user.getPassword();
            String hashedPassword = hashService.getHashedValue(password,encodedSalt);

            if(encodedPassword.equals(hashedPassword)){
                return new UsernamePasswordAuthenticationToken(username,password,new ArrayList<>());
            }
        }


        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
