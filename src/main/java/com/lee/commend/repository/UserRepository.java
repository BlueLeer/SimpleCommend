package com.lee.commend.repository;

import com.lee.commend.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

/**
 * @author lee
 * @date 2019/12/31 15:18
 */
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {
    Optional<User> findById(Integer id);
}
