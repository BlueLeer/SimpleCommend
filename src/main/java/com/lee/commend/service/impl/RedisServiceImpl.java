package com.lee.commend.service.impl;

import com.lee.commend.model.UserLiked;
import com.lee.commend.model.UserLikedCountDTO;
import com.lee.commend.model.UserLikedEnum;
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
    public void saveLiked2Redis(String likedUserId, String postLikedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, postLikedUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, UserLikedEnum.LIKED_STATUS.getCode());
    }

    @Override
    public void unLikedFromRedis(String unLikedUserId, String postUnLikedUserId) {
        String key = RedisKeyUtils.getLikedKey(unLikedUserId, postUnLikedUserId);
        redisTemplate.opsForHash().put(RedisKeyUtils.MAP_KEY_USER_LIKED, key, UserLikedEnum.UN_LIKE_STATUS.getCode());
    }

    @Override
    public void deleteLikedFromRedis(String likedUserId, String postLikedUserId) {
        String key = RedisKeyUtils.getLikedKey(likedUserId, postLikedUserId);
        redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
    }

    @Override
    public void incrementLikedCount(String likedUserId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, 1);
    }

    @Override
    public void decrementLikedCount(String likedUserId) {
        redisTemplate.opsForHash().increment(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, likedUserId, -1);
    }

    @Override
    public List<UserLiked> getLikedDataFromRedis() {
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED, ScanOptions.NONE);
        List<UserLiked> userLikedList = new ArrayList<>();
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> next = cursor.next();
            String key = (String) next.getKey();
            Integer status = (Integer) next.getValue();
            UserLiked userLiked = new UserLiked();
            String[] split = key.split(RedisKeyUtils.LIKED_POST_USER_ID_SPLIT);
            userLiked.setLikedUserId(split[0]);
            userLiked.setPostUserId(split[1]);
            userLiked.setStatus(status);

            userLikedList.add(userLiked);

            // 从redis取出来以后,将redis里面的删除掉
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED, key);
        }
        return userLikedList;
    }

    @Override
    public List<UserLikedCountDTO> getUserLikedCountFromRedis() {
        List<UserLikedCountDTO> likedCountList = new ArrayList<>();
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, ScanOptions.NONE);
        while (cursor.hasNext()) {
            Map.Entry<Object, Object> next = cursor.next();
            Integer userId = (Integer) next.getKey();
            Integer count = (Integer) next.getValue();
            UserLikedCountDTO dto = new UserLikedCountDTO(userId, count);
            likedCountList.add(dto);

            // 从redis取出来以后,将redis里面的删除掉
            redisTemplate.opsForHash().delete(RedisKeyUtils.MAP_KEY_USER_LIKED_COUNT, userId);
        }
        return likedCountList;
    }
}
