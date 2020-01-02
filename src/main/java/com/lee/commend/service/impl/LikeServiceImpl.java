package com.lee.commend.service.impl;

import com.lee.commend.constant.UserLikeEnum;
import com.lee.commend.domain.User;
import com.lee.commend.domain.UserLike;
import com.lee.commend.model.UserLikeCountDTO;
import com.lee.commend.repository.LikeUserRepository;
import com.lee.commend.repository.UserRepository;
import com.lee.commend.service.LikeService;
import com.lee.commend.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lee
 * @date 2019/12/31 14:39
 */
@Service
@Slf4j
@Transactional(readOnly = true)
public class LikeServiceImpl implements LikeService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private LikeUserRepository likeUserRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserLike save(UserLike userLike) {
        UserLike save = likeUserRepository.save(userLike);
        return save;
    }

    @Override
    @Transactional
    public List<UserLike> saveAll(List<UserLike> userLikeList) {
        userLikeList.forEach(userLike -> userLike.setCreateTime(LocalDateTime.now()));
        List<UserLike> userLikes = (List<UserLike>) likeUserRepository.saveAll(userLikeList);
        return userLikes;

    }

    @Override
    public Page<UserLike> getLikeListByLikeUserId(String likeUserId, Pageable pageable) {
        return likeUserRepository.findByLikeUserIdAndStatus(likeUserId, UserLikeEnum.LIKE_STATUS.getCode(), pageable);
    }

    @Override
    public Page<UserLike> getLikeListByPostUserId(String postUserId, Pageable pageable) {
        return likeUserRepository.findByPostUserIdAndStatus(postUserId, UserLikeEnum.LIKE_STATUS.getCode(), pageable);
    }

    @Override
    public UserLike getByLikeUserIdAndPostUserId(String likeUserId, String postUserId) {
        return likeUserRepository.findByLikeUserIdAndPostUserId(likeUserId, postUserId);
    }

    @Override
    @Transactional
    public void transLikeFromRedis2DB() {
        List<UserLike> likedDataFromRedis = redisService.getLikedDataFromRedis();
        likedDataFromRedis.forEach(like -> {
            UserLike userLike = getByLikeUserIdAndPostUserId(like.getLikeUserId(), like.getPostUserId());
            if (userLike == null) {
                like.setCreateTime(LocalDateTime.now());
                save(like);
            } else {
                userLike.setStatus(like.getStatus());
                userLike.setUpdateTime(LocalDateTime.now());
                save(userLike);
            }
        });
    }

    @Override
    @Transactional
    public void transLikeCountFromRedis2DB() {
        List<UserLikeCountDTO> countFromRedis = redisService.getUserLikeCountFromRedis();
        countFromRedis.forEach(c -> {
            User user = userRepository.findById(c.getUserId()).orElse(null);
            if (user != null) {
                int count = c.getLikeCount() + user.getLikedCount();
                user.setLikedCount(count);
                // 更新点赞数量
                userRepository.save(user);
            }
        });
    }
}
