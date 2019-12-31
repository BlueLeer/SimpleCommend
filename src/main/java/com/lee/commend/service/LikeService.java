package com.lee.commend.service;

import com.lee.commend.domain.UserLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author lee
 * @date 2019/12/31 14:29
 */
public interface LikeService {
    /**
     * 保存点赞记录
     * @param userLike
     * @return
     */
    UserLike save(UserLike userLike);

    /**
     * 批量保存
     * @param userLikeList
     * @return
     */
    List<UserLike> saveAll(List<UserLike> userLikeList);


    /**
     * 根据被点赞人ID查询点赞列表
     * @param LikeUserId
     * @param pageable
     * @return
     */
    Page<UserLike> getLikeListByLikeUserId(String LikeUserId, Pageable pageable);

    /**
     * 根据点赞人ID查询点赞列表
     * @param postUserId
     * @param pageable
     * @return
     */
    Page<UserLike> getLikeListByPostUserId(String postUserId,Pageable pageable);

    /**
     * 根据点赞人ID和被点赞人ID查询记录
     * @param LikeUserId
     * @param postUserId
     * @return
     */
    UserLike getByLikeUserIdAndPostUserId(String LikeUserId,String postUserId);

    /**
     * 将Redis里面的点赞数据存入到数据库中
     */
    void transLikeFromRedis2DB();

    /**
     * 将Redis中的点赞数量数据存入到数据库中
     */
    void transLikeCountFromRedis2DB();
}
