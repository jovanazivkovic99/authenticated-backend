package com.jovana.authenticatedbackend.services;

import com.jovana.authenticatedbackend.models.ApplicationUser;
import com.jovana.authenticatedbackend.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    
    private final PasswordEncoder encoder;
    
    // looks for users that we want
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
        System.out.println("In the user detail service");
        
        if(!username.equals("Jovana")) throw  new UsernameNotFoundException("Not Jovana");
        
        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "USER"));
        return new ApplicationUser(1,"Jovana", encoder.encode("password"), roles);
    }
}
