package com.kiszka.forumapp.validation;

import com.kiszka.forumapp.entity.ForumUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ForumUserServiceImpl implements ForumUserService {
    private final ForumUserRepository forumUserRepository;
    private final PasswordEncoder passwordEncoder;

    public ForumUserServiceImpl(ForumUserRepository forumUserRepository, PasswordEncoder passwordEncoder){
        this.forumUserRepository = forumUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(ForumUserDto forumUserDto) {
        ForumUser forumUser = new ForumUser();
        forumUser.setEmail(forumUserDto.getEmail());
        forumUser.setNickname(forumUserDto.getNickname());
        forumUser.setPassword(passwordEncoder.encode(forumUserDto.getPassword()));
        forumUser.setRole(forumUserDto.getRole());
        forumUserRepository.save(forumUser);
    }

    @Override
    public ForumUser findUserByEmail(String email) {
        return forumUserRepository.findByEmail(email);
    }

    @Override
    public ForumUser getAdmin() {
        return forumUserRepository.findByRole("ADMIN");
    }

    @Override
    public List<ForumUserDto> findAllUsers() {
        List<ForumUser> users = forumUserRepository.findAll();
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public ForumUser getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            String email = ((UserDetails)principal).getUsername();
            return forumUserRepository.findByEmail(email);
        }
        return null;
    }

    private ForumUserDto mapToUserDto(ForumUser forumUser){
        ForumUserDto forumUserDto = new ForumUserDto();
        forumUserDto.setEmail(forumUser.getEmail());
        return forumUserDto;
    }
}
