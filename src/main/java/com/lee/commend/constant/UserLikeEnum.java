package com.lee.commend.constant;

import lombok.Getter;

/**
 * 点赞状态枚举
 *
 * @author lee
 * @date 2019/12/27 11:21
 */
@Getter
public enum UserLikeEnum {
    Like_STATUS(1, "已赞"), UN_LIKE_STATUS(0, "未赞");
    private int code;
    private String message;

    UserLikeEnum(int code, String message) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

}
