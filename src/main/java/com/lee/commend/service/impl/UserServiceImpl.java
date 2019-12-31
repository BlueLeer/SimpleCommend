package com.lee.commend.service.impl;

import com.lee.commend.domain.User;
import com.lee.commend.repository.UserRepository;
import com.lee.commend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lee
 * @date 2019/12/31 15:46
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> saveAll(List<User> users) {
        Iterable<User> userList = userRepository.saveAll(users);
        return (List<User>) userList;
    }
}
