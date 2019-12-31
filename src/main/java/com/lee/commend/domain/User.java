package com.lee.commend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * @author lee
 * @date 2019/12/31 15:11
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user", indexes = {@Index(name = "user_id", columnList = "id", unique = true)})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "username")
    private String username;

    /**
     * 被点赞的数量
     */
    @Column(name = "liked_count")
    private Integer likedCount;
}
