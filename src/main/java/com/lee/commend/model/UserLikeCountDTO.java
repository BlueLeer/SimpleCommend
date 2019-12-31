package com.lee.commend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lee
 * @date 2019/12/27 11:18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLikeCountDTO {
    private Integer userId;
    private Integer LikeCount;
}
