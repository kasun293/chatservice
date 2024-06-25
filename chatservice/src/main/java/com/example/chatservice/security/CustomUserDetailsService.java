package com.example.chatservice.security;

import com.example.chatservice.entity.User;
import com.example.chatservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {




        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));



        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                user.getAuthorities()
        );
    }

}
