package com.sales.configuration;

import com.sales.domain.user.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
@Scope("prototype")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthUser authUser;

    @Autowired
    public UserDetailsServiceImpl(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        authUser.setAuthUserByUserId(username);
        return new User(authUser.getId(), authUser.getPassword(), AuthorityUtils.createAuthorityList(authUser.getAuthorityValue()));
    }
}
