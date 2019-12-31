package com.lee.commend.service;

import com.lee.commend.domain.UserLike;
import com.lee.commend.model.UserLikeCountDTO;

import java.util.List;

/**
 * @author lee
 * @date 2019/12/27 11:09
 */
public interface RedisService {
    /**
     * 点赞 新添加一条数据,状态为1
     *
     * @param LikeUserId     被点赞人ID
     * @param postLikeUserId 点赞人ID
     */
    void saveLike2Redis(String LikeUserId, String postLikeUserId);

    /**
     * 取消点赞.将记录的状态修改为0
     *
     * @param unLikeUserId
     * @param postUnLikeUserId
     */
    void unLikeFromRedis(String unLikeUserId, String postUnLikeUserId);

    /**
     * 删除一条点赞记录
     *
     * @param LikeUserId
     * @param postLikeUserId
     */
    void deleteLikeFromRedis(String LikeUserId, String postLikeUserId);

    /**
     * 被点赞人的点赞数加1
     *
     * @param LikeUserId
     */
    void incrementLikeCount(String LikeUserId);

    /**
     * 被点赞人的点赞数减1
     *
     * @param LikeUserId
     */
    void decrementLikeCount(String LikeUserId);

    /**
     * 获取Redis中存储的所有的点赞数据
     *
     * @return
     */
    List<UserLike> getLikedDataFromRedis();


    /**
     * 获取Redis中存储的所有的点赞数量数据
     * @return
     */
    List<UserLikeCountDTO> getUserLikeCountFromRedis();


}
