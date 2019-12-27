package com.lee.commend.util;

/**
 * @author lee
 * @date 2019/12/27 10:50
 */
public class RedisKeyUtils {
    /**
     * 保存用户点赞数据的key
     */
    public static final String MAP_KEY_USER_LIKED = "map_key_user_liked";

    /**
     * 保存用户被点赞数量的key
     */
    public static final String MAP_KEY_USER_LIKED_COUNT = "map_key_user_liked_count";
    
    public static final String LIKED_POST_USER_ID_SPLIT = "::";

    /**
     * 根据被点赞人(likedUserId)  和 点赞人(likedPostId) 生成key,例如: 23232:34343
     *
     * @param likedUserId 被点赞人ID
     * @param likedPostID 点赞人ID
     * @return
     */
    public static String getLikedKey(String likedUserId, String likedPostID) {
        StringBuilder sb = new StringBuilder();
        return sb.append(likedUserId).append("::").append(likedPostID).toString();
    }
}
