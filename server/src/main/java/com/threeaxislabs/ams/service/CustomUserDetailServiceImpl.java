package com.threeaxislabs.ams.service;

import com.threeaxislabs.ams.config.CustomUserDetailsImpl;
import com.threeaxislabs.ams.entity.UserDetail;
import com.threeaxislabs.ams.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDetail> userDetail = userRepository.findByName(username);
        return userDetail.map(CustomUserDetailsImpl::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }
}
