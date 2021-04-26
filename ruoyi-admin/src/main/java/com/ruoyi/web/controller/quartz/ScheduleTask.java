package com.ruoyi.web.controller.quartz;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BusiOperation;
import com.ruoyi.system.service.IBusiArticleService;
import com.ruoyi.system.service.IBusiOperationService;
import com.ruoyi.system.service.IBusiPraiseService;
import com.ruoyi.common.constant.RedisConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dfm
 * @create 2021-04-20 17:39
 */
@Component("myTask")
public class ScheduleTask {


    @Autowired
    IBusiOperationService operationService;

    @Autowired
    IBusiPraiseService praiseService;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    IBusiArticleService articleService;
    //每天12点清空数据
    public void flush(){

    }

    //同步踩量 数据
    public void updateTrample(){
        System.out.println("踩一踩 信息同步mysql开始！");
        //同步 文章 被踩 信息
        Map<Object, Object> userLikeArticle = redisTemplate.opsForHash().entries(RedisConstans.USER_TRAMPLE_ARTICLE_KEY);
        Set<Map.Entry<Object, Object>> entries = userLikeArticle.entrySet();
        for (Map.Entry<Object,Object> entry:entries
        ) {
            // if("1".equals(entry.getValue())){
            Object key = entry.getKey();
            String[] split = String.valueOf(key).split("::");// user::articleID
            BusiOperation busiOperation = new BusiOperation();

            busiOperation.setOperationUser(Long.valueOf(split[0]));
            busiOperation.setEntityId(Long.valueOf(split[1]));
            busiOperation.setEntityType(1);
            busiOperation.setOperationType(1);

            //查询是否有数据，有则只能有 1条
            List<BusiOperation> busiOperations = operationService.selectBusiOperationList(busiOperation);
            busiOperation.setCreateBy("admin");//设置为超级管理员
            busiOperation.setOperationTime(DateUtils.getNowDate());
            busiOperation.setCreateTime(DateUtils.getNowDate());
            busiOperation.setStatus(String.valueOf(entry.getValue()));
            if(busiOperations != null && busiOperations.size() == 1){// 有数据 则 update
                busiOperation.setId(busiOperations.get(0).getId());
                operationService.updateBusiOperation(busiOperation);
            }else{// 无数据则 insert
                operationService.insertBusiOperation(busiOperation);
            }
            //}
        }
        //同步 评论被踩 信息
        Map<Object, Object> userLikeComment = redisTemplate.opsForHash().entries(RedisConstans.USER_TRAMPLE_COMMENT_KEY);
        entries = userLikeComment.entrySet();
        for (Map.Entry<Object,Object> entry:entries
        ) {
            // if("1".equals(entry.getValue())){
            Object key = entry.getKey();
            String[] split = String.valueOf(key).split("::");// user::commentId
            BusiOperation busiOperation = new BusiOperation();

            busiOperation.setOperationUser(Long.valueOf(split[0]));
            busiOperation.setEntityId(Long.valueOf(split[1]));
            busiOperation.setEntityType(2);
            busiOperation.setOperationType(1);
            List<BusiOperation> busiOperations = operationService.selectBusiOperationList(busiOperation);
            busiOperation.setCreateBy("admin");//设置为超级管理员
            busiOperation.setOperationTime(DateUtils.getNowDate());
            busiOperation.setCreateTime(DateUtils.getNowDate());
            busiOperation.setStatus(String.valueOf(entry.getValue()));
            if(busiOperations != null && busiOperations.size() == 1){// 有数据 update 需要 id
                busiOperation.setId(busiOperations.get(0).getId());
                operationService.updateBusiOperation(busiOperation);
            }else {
                operationService.insertBusiOperation(busiOperation);
            }

        }
        System.out.println("踩一踩 信息同步mysql结束！");
    }

    //每隔两个小时 同步数据
    public void updatePraise(){
        System.out.println("点赞信息同步mysql开始");
        //同步 文章点赞信息
        Map<Object, Object> userLikeArticle = redisTemplate.opsForHash().entries(RedisConstans.USER_LIKE_ARTICLE_KEY);
        Set<Map.Entry<Object, Object>> entries = userLikeArticle.entrySet();
        for (Map.Entry<Object,Object> entry:entries
        ) {
           // if("1".equals(entry.getValue())){
                Object key = entry.getKey();
                String[] split = String.valueOf(key).split("::");// user::articleID
                BusiOperation busiOperation = new BusiOperation();

                busiOperation.setOperationUser(Long.valueOf(split[0]));
                busiOperation.setEntityId(Long.valueOf(split[1]));
                busiOperation.setEntityType(1);
                busiOperation.setOperationType(4);

                //查询是否有数据，有则只能有 1条
                List<BusiOperation> busiOperations = operationService.selectBusiOperationList(busiOperation);
                busiOperation.setCreateBy("admin");//设置为超级管理员
                busiOperation.setOperationTime(DateUtils.getNowDate());
                busiOperation.setCreateTime(DateUtils.getNowDate());
                busiOperation.setStatus(String.valueOf(entry.getValue()));
                if(busiOperations != null && busiOperations.size() == 1){// 有数据 则 update
                    busiOperation.setId(busiOperations.get(0).getId());
                    operationService.updateBusiOperation(busiOperation);
                }else{// 无数据则 insert
                    operationService.insertBusiOperation(busiOperation);
                }
            //}
        }
        //同步 评论点赞信息
        Map<Object, Object> userLikeComment = redisTemplate.opsForHash().entries(RedisConstans.USER_LIKE_COMMENT_KEY);
        entries = userLikeComment.entrySet();
        for (Map.Entry<Object,Object> entry:entries
        ) {
           // if("1".equals(entry.getValue())){
                Object key = entry.getKey();
                String[] split = String.valueOf(key).split("::");// user::commentId
                BusiOperation busiOperation = new BusiOperation();

                busiOperation.setOperationUser(Long.valueOf(split[0]));
                busiOperation.setEntityId(Long.valueOf(split[1]));
                busiOperation.setEntityType(2);
                busiOperation.setOperationType(4);
                List<BusiOperation> busiOperations = operationService.selectBusiOperationList(busiOperation);
                busiOperation.setCreateBy("admin");//设置为超级管理员
                busiOperation.setOperationTime(DateUtils.getNowDate());
                busiOperation.setCreateTime(DateUtils.getNowDate());
                busiOperation.setStatus(String.valueOf(entry.getValue()));
                if(busiOperations != null && busiOperations.size() == 1){// 有数据 update 需要 id
                    busiOperation.setId(busiOperations.get(0).getId());
                    operationService.updateBusiOperation(busiOperation);
                }else {
                    operationService.insertBusiOperation(busiOperation);
                }

        }
        System.out.println("点赞信息同步mysql结束！！！");
    }
}
