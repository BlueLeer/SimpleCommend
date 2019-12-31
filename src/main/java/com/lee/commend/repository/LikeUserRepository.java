package com.lee.commend.repository;

import com.lee.commend.domain.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author lee
 * @date 2019/12/31 14:42
 */
public interface LikeUserRepository extends PagingAndSortingRepository<UserLike, Integer> {
    Page<UserLike> findByLikeUserIdAndStatus(String likeUserId, Integer status, Pageable pageable);

    Page<UserLike> findByPostUserIdAndStatus(String postUserId, Integer status, Pageable pageable);
    
    UserLike findByLikeUserIdAndPostUserId(String likeUserId,String postUserId);
}
