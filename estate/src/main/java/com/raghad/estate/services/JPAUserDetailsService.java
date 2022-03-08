package com.raghad.estate.services;

import com.raghad.estate.configuration.SecurityUser;
import com.raghad.estate.models.User;
import com.raghad.estate.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JPAUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public JPAUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user != null)
            return new SecurityUser(user.getUsername(), user.getPassword(), user.getAuthority());
        throw new UsernameNotFoundException("User '" + username + "' Not Found");
    }
}
