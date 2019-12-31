package com.lee.commend;

import com.lee.commend.domain.User;
import com.lee.commend.domain.UserLike;
import com.lee.commend.service.LikeService;
import com.lee.commend.service.RedisService;
import com.lee.commend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author lee
 * @date 2019/12/31 15:36
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CommendApplicationTest {
    @Autowired
    private RedisService redisService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private LikeService likeService;

    @Test
    public void testCreateUser() {
        List<User> userList = Arrays.asList(
                new User(null, "张三", 0),
                new User(null, "张三2", 0),
                new User(null, "张三3", 0),
                new User(null, "张三4", 0),
                new User(null, "张三5", 0),
                new User(null, "张三6", 0),
                new User(null, "张三7", 0)
        );
        
        userService.saveAll(userList);
    }

    /**
     * 测试用户的点赞行为:张三向 张三1、张三2、张三3、张三4、张三5、张三6点赞
     */
    @Test
    public void testSaveLikedList() {
        redisService.saveLike2Redis(String.valueOf(1),String.valueOf(2));
        redisService.saveLike2Redis(String.valueOf(1),String.valueOf(3));
        redisService.saveLike2Redis(String.valueOf(1),String.valueOf(4));
        redisService.saveLike2Redis(String.valueOf(1),String.valueOf(5));
        redisService.saveLike2Redis(String.valueOf(1),String.valueOf(6));
        
    }
    
    @Test
    public void testTransUserLikeFromRedis2DB(){
        List<UserLike> likedDataFromRedis = redisService.getLikedDataFromRedis();
        log.info(likedDataFromRedis.toString());
        List<UserLike> userLikes = likeService.saveAll(likedDataFromRedis);
        log.info(userLikes.toString());

    }
}
