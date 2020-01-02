package com.lee.commend.task;

import com.lee.commend.service.LikeService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author lee
 * @date 2020/1/2 13:34
 */
@Slf4j
public class LikeTask extends QuartzJobBean {
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private LikeService likeService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("LikeTask-------{}", LocalDateTime.now().format(dtf));
        
        likeService.transLikeFromRedis2DB();
        likeService.transLikeCountFromRedis2DB();
    }
}
