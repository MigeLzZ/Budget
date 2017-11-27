package com.budget.services;

import com.budget.dao.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailsService extends UserDetailsService {
    void changeUsername(User user, String password);
}
