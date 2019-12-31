package com.lee.commend.domain;

import com.lee.commend.constant.UserLikeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author lee
 * @date 2019/12/27 11:16
 */
@Data
@Builder
@Entity
@Table(name = "user_Like", indexes = {
        @Index(name = "index_Like_user_id", columnList = "Like_user_id", unique = false),
        @Index(name = "index_post_user_id", columnList = "post_user_id", unique = false)
})
@NoArgsConstructor
@AllArgsConstructor
public class UserLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "Like_user_id")
    private String likeUserId;
    
    @Column(name = "post_user_id")
    private String postUserId;

    /**
     * 点赞状态,默认为未赞
     */
    @Column(name = "status")
    private Integer status = UserLikeEnum.UN_LIKE_STATUS.getCode();
    
    @Column(name = "create_time")
    private LocalDateTime createTime;
    
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
