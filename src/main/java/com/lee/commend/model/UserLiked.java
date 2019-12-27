package com.lee.commend.model;

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
@Table(name = "user_liked", indexes = {
        @Index(name = "index_liked_user_id", columnList = "liked_user_id", unique = true),
        @Index(name = "index_post_user_id", columnList = "post_user_id", unique = true)
})
@NoArgsConstructor
@AllArgsConstructor
public class UserLiked {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "liked_user_id")
    private String likedUserId;
    @Column(name = "post_user_id")
    private String postUserId;
    
    private Integer status;
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
}
