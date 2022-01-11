package com.myshop.security;


import com.myshop.user.model.User;
import com.myshop.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Resource
    public UserRepository userRepository;


    /**
     * @param username  valid username
     * @return found user details
     * @throws UsernameNotFoundException throws if the username cannot be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(username);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " +username));

        return user.map(MyUserDetails::new).get();
    }
}
