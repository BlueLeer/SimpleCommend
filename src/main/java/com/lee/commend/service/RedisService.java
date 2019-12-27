package com.lee.commend.service;

import com.lee.commend.model.UserLiked;
import com.lee.commend.model.UserLikedCountDTO;

import java.util.List;

/**
 * @author lee
 * @date 2019/12/27 11:09
 */
public interface RedisService {
    /**
     * 点赞 新添加一条数据,状态为1
     *
     * @param likedUserId     被点赞人ID
     * @param postLikedUserId 点赞人ID
     */
    void saveLiked2Redis(String likedUserId, String postLikedUserId);

    /**
     * 取消点赞.将记录的状态修改为0
     *
     * @param unLikedUserId
     * @param postUnLikedUserId
     */
    void unLikedFromRedis(String unLikedUserId, String postUnLikedUserId);

    /**
     * 删除一条点赞记录
     *
     * @param likedUserId
     * @param postLikedUserId
     */
    void deleteLikedFromRedis(String likedUserId, String postLikedUserId);

    /**
     * 被点赞人的点赞数加1
     *
     * @param likedUserId
     */
    void incrementLikedCount(String likedUserId);

    /**
     * 被点赞人的点赞数减1
     *
     * @param likedUserId
     */
    void decrementLikedCount(String likedUserId);

    /**
     * 获取Redis中存储的所有的点赞数据
     *
     * @return
     */
    List<UserLiked> getLikedDataFromRedis();


    /**
     * 获取Redis中存储的所有的点赞数量数据
     * @return
     */
    List<UserLikedCountDTO> getUserLikedCountFromRedis();


}
