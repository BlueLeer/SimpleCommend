package com.lee.commend.service.impl;

import com.lee.commend.domain.UserLike;
import com.lee.commend.model.UserLikeCountDTO;
import com.lee.commend.constant.UserLikeEnum;
import com.lee.commend.service.RedisService;
import com.lee.commend.util.RedisKeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lee
 * @date 2019/12/27 11:19
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void saveLike2Redis(String LikeUserId, String postLikeUserId) {
        String key = RedisKeyUtils.getLikeKey(LikeUserId, postLikeUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKE, key, UserLikeEnum.Like_STATUS.getCode());
    }

    @Override
    public void unLikeFromRedis(String unLikeUserId, String postUnLikeUserId) {
        String key = RedisKeyUtils.getLikeKey(unLikeUserId, postUnLikeUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKE, key, UserLikeEnum.UN_LIKE_STATUS.getCode());
    }

    @Override
    public void deleteLikeFromRedis(String LikeUserId, String postLikeUserId) {
        String key = RedisKeyUtils.getLikeKey(LikeUserId, postLikeUserId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKE, key);
    }

    @Override
    public void incrementLikeCount(String LikeUserId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKE_COUNT, LikeUserId, 1);
    }

    @Override
    public void decrementLikeCount(String LikeUserId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKE_COUNT, LikeUserId, -1);
    }

    @Override
    public List<UserLike> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKE, ScanOptions.NONE);
        List<UserLike> userLikeList = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> next = cursor.next();
            String key = (String) next.getKey();
            Integer status = (Integer) next.getValue();
            UserLike userLike = new UserLike();
            String[] split = key.split(RedisKeyUtils.LIKE_POST_USER_ID_SPLIT);
            userLike.setLikeUserId(split[0]);
            userLike.setPostUserId(split[1]);
            userLike.setStatus(status);

            userLikeList.add(userLike);

            // 从redis取出来以后,将redis里面的删除掉
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKE, key);
        }
        return userLikeList;
    }

    @Override
    public List<UserLikeCountDTO> getUserLikeCountFromRedis() {
        List<UserLikeCountDTO> LikeCountList = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKE_COUNT, ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> next = cursor.next();
            Integer userId = (Integer) next.getKey();
            Integer count = (Integer) next.getValue();
            UserLikeCountDTO dto = new UserLikeCountDTO(userId, count);
            LikeCountList.add(dto);

            // 从redis取出来以后,将redis里面的删除掉
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKE_COUNT, userId);
        }
        return LikeCountList;
    }
}
