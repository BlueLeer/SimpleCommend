package com.lee.commend.service;

import com.lee.commend.domain.User;

import java.util.List;

/**
 * @author lee
 * @date 2019/12/31 15:45
 */
public interface UserService {
    List<User> saveAll(List<User> users);
}
