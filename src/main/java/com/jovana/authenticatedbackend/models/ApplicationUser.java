package com.jovana.authenticatedbackend.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class ApplicationUser implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;
    
    private String username;
    private String password;
    
    // EAGER - fetch data for authorities as soon as we fetch user
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role_junction",
            joinColumns = {@JoinColumn(name = "user_id")},// columns from this table (users)
            inverseJoinColumns = {@JoinColumn(name = "role_id")}
    )
    private Set<Role> authorities;
    
    public ApplicationUser(){
        super();
        this.authorities = new HashSet<>();
    }
    
    public ApplicationUser (Integer userId, String username, String password, Set<Role> authorities) {
        super();
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    
    public Integer getUserId () {
        return userId;
    }
    
    public void setUserId (Integer userId) {
        this.userId = userId;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities () {
        return this.authorities;
    }
    
    public void setAuthorities (Set<Role> authorities) {
        this.authorities = authorities;
    }
    
    @Override
    public String getPassword () {
        return this.password;
    }
    
    public void setPassword (String password) {
        this.password = password;
    }
    
    @Override
    public String getUsername () {
        return this.username;
    }
    
    public void setUsername (String username) {
        this.username = username;
    }
     // false - account expired
    // true - account active
    @Override
    public boolean isAccountNonExpired () {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked () {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired () {
        return true;
    }
    
    @Override
    public boolean isEnabled () {
        return true;
    }
}
