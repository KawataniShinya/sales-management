package com.managiment.common;

import com.managiment.domain.user.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthUser authUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Map<String, Object>> resultMap = authUser.getAuthUser(username);
        if (resultMap.isEmpty() || resultMap.size() > 1) {
            throw new UsernameNotFoundException("not found : " + username);
        }
        return new User((String) resultMap.get(0).get("LOGIN_ID"), (String) resultMap.get(0).get("PASSWORD"), AuthorityUtils.createAuthorityList((String) resultMap.get(0).get("ROLE")));
    }
}
