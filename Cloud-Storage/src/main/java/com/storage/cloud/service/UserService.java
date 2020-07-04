package com.storage.cloud.service;

import com.storage.cloud.mapper.UserMapper;
import com.storage.cloud.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUserNameAvailable(String username){

        User existingUser = userMapper.getUser(username);

        return existingUser!=null?false:true;
    }

    public int addUser(User user){

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.addUser(new User(null, user.getUsername(), encodedSalt,
                hashedPassword, user.getFirstname(), user.getLastname()));

    }

    public User getUser(String username){
        return userMapper.getUser(username);
    }
}
