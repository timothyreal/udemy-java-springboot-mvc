package com.training.demosecurity.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.training.demosecurity.constant.UserRole;
import com.training.demosecurity.dao.RoleDao;
import com.training.demosecurity.dao.UserDao;
import com.training.demosecurity.entity.Role;
import com.training.demosecurity.entity.User;
import com.training.demosecurity.user.WebUser;

import jakarta.transaction.Transactional;

@Service 
public class UserServiceImpl implements UserService {

    private final UserDao _userDao;
    private final RoleDao _roleDao;

    private final BCryptPasswordEncoder _passwordEncoder;

    public UserServiceImpl(UserDao _userDao, RoleDao _roleDao, BCryptPasswordEncoder passwordEncoder) {
        this._userDao = _userDao;
        this._roleDao = _roleDao;
        this._passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this._userDao.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        Collection<SimpleGrantedAuthority> authorities = this.mapRolesToAuthorities(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                authorities);
    }

    @Override
    public User findByUserName(String userName) {
        return this._userDao.findByUserName(userName);
    }

    @Override
    @Transactional 
    public void save(WebUser webUser) {
        User user = new User();

        user.setUserName(webUser.getUserName());
        user.setPassword(this._passwordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
		user.setLastName(webUser.getLastName());
		user.setEmail(webUser.getEmail());
		user.setEnabled(true);

        user.setRoles(Arrays.asList(this._roleDao.findRoleByName(UserRole.EMPLOYEE.getRole())));

        this._userDao.save(user);
    }

    private Collection<SimpleGrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role tempRole : roles) {
            SimpleGrantedAuthority tempAuthority = new SimpleGrantedAuthority(tempRole.getName());
            authorities.add(tempAuthority);
        }

        return authorities;
    }

}
