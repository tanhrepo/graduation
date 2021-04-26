package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.RedisConstans;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IBusiPraiseService;
import com.ruoyi.system.service.IBusiTrampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dfm
 * @create 2021-04-16 19:35
 */
@Service
public class BusiTrampleServiceImpl implements IBusiTrampleService {

    @Autowired
    StringRedisTemplate redisTemplate;


    private void associationOperation(String key ,Long id){
        Object total =  redisTemplate.opsForHash().get(key, String.valueOf(id));
        if(total != null){//   有被踩记录
            //被踩数 +1
            redisTemplate.opsForHash().increment(key,String.valueOf(id),1l);
        }else{
            // 设置初始 被踩数 1
             redisTemplate.opsForHash().put(key,String.valueOf(id),"1");
        }
    }

    /**
     * 踩
     * @param entityId   被踩对象ID
     * @param likedUserId 被踩用户ID
     * @param userId 踩用户
     * @param eneityType  实体类型(1article 2comment)
     */
    public AjaxResult doPraise(Long entityId, Long likedUserId, Long userId, Integer eneityType) {
        if(eneityType == 1){// article
            //只有未踩的用户才可以进行踩
            Object status = redisTemplate.opsForHash().get(RedisConstans.USER_TRAMPLE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId));
            if(status !=null && status != "0")//不存在 踩或 取消踩记录
            {

                //进行踩status 置 1
                redisTemplate.opsForHash().put(RedisConstans.USER_TRAMPLE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId),"1");

                //用户文章 总被点数 +1
                associationOperation(RedisConstans.USER_ARTICLE_TOTAL_TRAMPLED_COUNT_KEY,likedUserId);
                // 文章 总被踩数 +1
                associationOperation(RedisConstans.ARTICLE_TOTAL_TRAMPLE_COUNT_KEY,entityId);
                return AjaxResult.success("踩成功");

            }else if(status == null){ // 未存在 踩记录
                //缓存踩记录 status 为 1
                 redisTemplate.opsForHash().put(RedisConstans.USER_TRAMPLE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId),"1");

                //用户文章 总被点数 +1
                associationOperation(RedisConstans.USER_ARTICLE_TOTAL_TRAMPLED_COUNT_KEY,likedUserId);
                // 文章 总被踩数 +1
                associationOperation(RedisConstans.ARTICLE_TOTAL_TRAMPLE_COUNT_KEY,entityId);
                return AjaxResult.success("踩成功");
            }else  {// status = "1"， 已踩
                return AjaxResult.error("已踩，无需重复踩！");
            }
        }else {// entityType == 2 comment
            //只有未踩的用户才可以进行踩
            Object status =  redisTemplate.opsForHash().get(RedisConstans.USER_TRAMPLE_COMMENT_KEY, String.valueOf(userId + "::" + entityId));
            if(status !=null && status != "0")//不存在 踩或 取消踩记录
            {
                //进行踩status 置 1
                redisTemplate.opsForHash().put(RedisConstans.USER_TRAMPLE_COMMENT_KEY, String.valueOf(userId + "::" + entityId),"1");
                associationOperation(RedisConstans.USER_COMMENT_TOTAL_TRAMPLED_COUNT_KEY,likedUserId);
                associationOperation(RedisConstans.COMMENT_TOTAL_TRAMPLE_COUNT_KEY,entityId);
                return AjaxResult.success("踩成功");
            }else if(status == null){ // 未存在 踩记录
                //缓存踩记录 status 为 1
                 redisTemplate.opsForHash().put(RedisConstans.USER_TRAMPLE_COMMENT_KEY, String.valueOf(userId + "::" + entityId),"1");
                associationOperation(RedisConstans.USER_COMMENT_TOTAL_TRAMPLED_COUNT_KEY,likedUserId);
                associationOperation(RedisConstans.COMMENT_TOTAL_TRAMPLE_COUNT_KEY,entityId);
                return AjaxResult.success("踩成功");
            }else {// status = "1"， 已踩
                return  AjaxResult.error("已踩，无需重复踩！");
            }
        }
    }

    /**
     * 取消踩
     * @param entityId   被踩对象ID
     * @param likedUserId 被踩用户ID
     * @param userId 踩用户
     * @param eneityType 实体类型
     */
    @Override
    public AjaxResult doUnPraise(Long entityId, Long likedUserId, Long userId, Integer eneityType) {
        if(eneityType == 1){//article
            Object status = redisTemplate.opsForHash().get(RedisConstans.USER_TRAMPLE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId));
            if(status != null && status != "0"){
                //修改 踩状态为 0
                redisTemplate.opsForHash().put(RedisConstans.USER_TRAMPLE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId),"0");
                //被踩用户的 总被踩数-1
                unLikeAssociation(RedisConstans.USER_ARTICLE_TOTAL_TRAMPLED_COUNT_KEY,likedUserId);
                // 该文章 总被踩数 -1
                unLikeAssociation(RedisConstans.ARTICLE_TOTAL_TRAMPLE_COUNT_KEY,entityId);
                return  AjaxResult.success("取消踩成功！");
            }else{// 无踩记录
                return AjaxResult.error("该用户未对本文章踩！");
            }
        }else {// comment
            Object status = redisTemplate.opsForHash().get(RedisConstans.USER_TRAMPLE_COMMENT_KEY, String.valueOf(userId + "::" + entityId));
            if(status != null && status != "0"){
                //修改 踩状态为 0
                redisTemplate.opsForHash().put(RedisConstans.USER_TRAMPLE_COMMENT_KEY, String.valueOf(userId + "::" + entityId),"0");
                //被踩用户的 总被踩数-1
                unLikeAssociation(RedisConstans.USER_COMMENT_TOTAL_TRAMPLED_COUNT_KEY,likedUserId);
                // 该文章 总被踩数 -1
                unLikeAssociation(RedisConstans.COMMENT_TOTAL_TRAMPLE_COUNT_KEY,entityId);
                return  AjaxResult.success("取消踩成功！");
            }else{// 无踩记录
                return AjaxResult.error("该用户未对本评论踩！");
            }
        }
    }

    /**
     * 取消踩管理操作
     * @param key
     * @param id
     */
    private void unLikeAssociation(String key,Long id){
        Object count = redisTemplate.opsForHash().get(key, String.valueOf(id));
        Long total = Long.valueOf((String) count);
        redisTemplate.opsForHash().put(key, String.valueOf(id),String.valueOf(total-1));
    }

    /**
     * 统计entity  的踩总数
     * @param entityId
     * @param eneityType 实体类型
     * @return
     */
    @Override
    public Long countTotalLike(Long entityId, Integer eneityType) {
        if(eneityType == 1){// article
            Object count = redisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_TRAMPLE_COUNT_KEY, String.valueOf(entityId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }else {// comment
            Object count = redisTemplate.opsForHash().get(RedisConstans.COMMENT_TOTAL_TRAMPLE_COUNT_KEY, String.valueOf(entityId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }
    }

    /**
     * 统计 用户 entity总被踩数
     * @param likedUserId
     * @param entityType  实体类型
     * @return
     */
    @Override
    public Long countUserLike(Long likedUserId, Integer entityType) {
        if(entityType == 1){// article
            Object count = redisTemplate.opsForHash().get(RedisConstans.USER_ARTICLE_TOTAL_TRAMPLED_COUNT_KEY, String.valueOf(likedUserId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }else {// comment
            Object count = redisTemplate.opsForHash().get(RedisConstans.USER_COMMENT_TOTAL_TRAMPLED_COUNT_KEY, String.valueOf(likedUserId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }
    }

    /**
     * 获取 用户 踩的 entity ids
     * @param userId 踩用户ID
     * @param entityType
     * @return
     */
    @Override
    public List<Long> getUserLikeentitys(Long userId, Integer entityType) {
        if(entityType == 1){// article
            List<Long> articleIds = new ArrayList<>();
            Map<Object, Object> userLikeArticle = redisTemplate.opsForHash().entries(RedisConstans.USER_TRAMPLE_ARTICLE_KEY);
            Set<Map.Entry<Object, Object>> entries = userLikeArticle.entrySet();
            for (Map.Entry<Object,Object> entry:entries
                 ) {
                if("1".equals(entry.getValue())){
                    Object key = entry.getKey();
                    String[] split = String.valueOf(key).split("::");// user::articleID
                    if(String.valueOf(split[0]).equals(String.valueOf(userId))){
                        articleIds.add(Long.valueOf((split[1])));
                    }

                }
            }
            return articleIds;
        }else{// comment
            List<Long> commentIds = new ArrayList<>();
            Map<Object, Object> userLikeComments = redisTemplate.opsForHash().entries(RedisConstans.USER_TRAMPLE_COMMENT_KEY);
            Set<Map.Entry<Object, Object>> entries = userLikeComments.entrySet();
            for (Map.Entry<Object,Object> entry:entries
            ) {
                if("1".equals(entry.getValue())){
                    Object key = entry.getKey();
                    String[] split = String.valueOf(key).split("::");// user::commentID
                    if(String.valueOf(split[0]).equals(String.valueOf(userId))){
                        commentIds.add(Long.valueOf((split[1])));
                    }
                }
            }
            return commentIds;
        }
    }


}
