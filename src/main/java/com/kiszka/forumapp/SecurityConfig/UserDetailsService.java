package com.kiszka.forumapp.SecurityConfig;


import com.kiszka.forumapp.entity.ForumUser;
import com.kiszka.forumapp.validation.ForumUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final ForumUserRepository forumUserRepository;

    @Autowired
    public UserDetailsService(ForumUserRepository forumUserRepository) {
        this.forumUserRepository = forumUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ForumUser user = forumUserRepository.findByEmail(email);
        if(user==null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
