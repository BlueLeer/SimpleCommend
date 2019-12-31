package com.lee.commend.util;

/**
 * @author lee
 * @date 2019/12/27 10:50
 */
public class RedisKeyUtils {
    /**
     * 保存用户点赞数据的key
     */
    public static final String MAP_KEY_USER_LIKE = "map_key_user_Like";

    /**
     * 保存用户被点赞数量的key
     */
    public static final String MAP_KEY_USER_LIKE_COUNT = "map_key_user_Like_count";
    
    public static final String LIKE_POST_USER_ID_SPLIT = "::";

    /**
     * 根据被点赞人(LikeUserId)  和 点赞人(LikePostId) 生成key,例如: 23232::34343
     *
     * @param LikeUserId 被点赞人ID
     * @param LikePostID 点赞人ID
     * @return
     */
    public static String getLikeKey(String LikeUserId, String LikePostID) {
        StringBuilder sb = new StringBuilder();
        return sb.append(LikeUserId).append("::").append(LikePostID).toString();
    }
}
