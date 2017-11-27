package com.budget.services;

import com.budget.dao.entities.Role;
import com.budget.dao.entities.User;
import com.budget.dao.repository.IRoleRepository;
import com.budget.dao.repository.IUserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.procedure.ProcedureCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) throws Exception {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;


    @Override
    @Transactional
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username);
            return user;
        }catch (Exception ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.delete(id);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void setUserRole(User user){
        long id = 2;
        Role role = roleRepository.findOne(id);
        HashSet<Role> roles = new HashSet<Role>();
        roles.add(role);
        user.setRoles(roles);
    }

    @Override
    @Transactional
    public User saveCurrentUserWithDetailsUpdate(User user) {
        User savedUser = userRepository.save(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser, savedUser.getPassword(), savedUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return savedUser;
    }

    @Override
    @Transactional
    public void changeUserPassword(String username, String currentPassword, String newPassword) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new Exception("");
        if (!user.getPassword().equals(currentPassword))
            throw new Exception("");
        user.setPassword(newPassword);
        userRepository.saveAndFlush(user);
    }



    @Override
    public boolean checkIfUserExists(String login) {
        return userRepository.checkIfUserExists(login);
    }

    @Override
    public boolean checkIfMailExists(String mail) {
        return userRepository.checkIfMailExists(mail);
    }

    @Override
    @Transactional
    public User changeUsername(long userId, String newUsername) {
        User user = userRepository.findOne(userId);
        user.setUsername(newUsername);
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User changeUserMail(long userId, String newMail) {
        User user = userRepository.findOne(userId);
        user.setMail(newMail);
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public User changeUserLimit(long userId, float limit) {
        User user = userRepository.findOne(userId);
        user.setMounthlyLimit(limit);
        return userRepository.saveAndFlush(user);
    }
}
