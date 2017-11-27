package com.budget.services;

import com.budget.dao.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailService implements IUserDetailsService {
    private final IUserService userService;

    @Autowired
    public UserDetailService(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        boolean userExists = userService.checkIfUserExists(login);
        if (userExists)
            return userService.findUserByUsername(login);
        else
            throw new UsernameNotFoundException("Not found");
    }

    @Override
    public void changeUsername(User user, String username) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) authentication.getPrincipal();
        userDetails.setUsername(username);
        userService.saveUser(userDetails);
    }
}
