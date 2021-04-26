package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.RedisConstans;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IBusiPraiseService;
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
public class BusiPraiseServiceImpl implements IBusiPraiseService {

    @Autowired
    StringRedisTemplate redisTemplate;


    private void associationOperation(String key ,Long id){
        Object total =  redisTemplate.opsForHash().get(key, String.valueOf(id));
        if(total != null){//   有被点赞 记录
            //被点赞数 +1
            redisTemplate.opsForHash().increment(key,String.valueOf(id),1l);
        }else{
            // 设置初始 被点赞数 1
             redisTemplate.opsForHash().put(key,String.valueOf(id),"1");
        }
    }

    /**
     * 点赞
     * @param entityId   被点赞对象ID
     * @param likedUserId 被点赞用户ID
     * @param userId 点赞用户
     * @param eneityType  实体类型(1article 2comment)
     */
    public AjaxResult doPraise(Long entityId, Long likedUserId, Long userId, Integer eneityType) {
        if(eneityType == 1){// article
            //只有未点赞的用户才可以进行点赞
            Object status = redisTemplate.opsForHash().get(RedisConstans.USER_LIKE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId));
            if(status !=null && status != "0")//不存在 点赞 或 取消点赞记录
            {

                //进行点赞 status 置 1
                redisTemplate.opsForHash().put(RedisConstans.USER_LIKE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId),"1");

                //用户文章 总被点数 +1
                associationOperation(RedisConstans.USER_ARTICLE_TOTAL_LIKED_COUNT_KEY,likedUserId);
                // 文章 总被点赞数 +1
                associationOperation(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY,entityId);
                return AjaxResult.success("点赞成功");

            }else if(status == null){ // 未存在 点赞记录
                //缓存点赞记录 status 为 1
                 redisTemplate.opsForHash().put(RedisConstans.USER_LIKE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId),"1");

                //用户文章 总被点数 +1
                associationOperation(RedisConstans.USER_ARTICLE_TOTAL_LIKED_COUNT_KEY,likedUserId);
                // 文章 总被点赞数 +1
                associationOperation(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY,entityId);
                return AjaxResult.success("点赞成功");
            }else  {// status = "1"， 已点赞
                return AjaxResult.error("已点赞，无需重复点赞！");
            }
        }else {// entityType == 2 comment
            //只有未点赞的用户才可以进行点赞
            Object status =  redisTemplate.opsForHash().get(RedisConstans.USER_LIKE_COMMENT_KEY, String.valueOf(userId + "::" + entityId));
            if(status !=null && status != "0")//不存在 点赞 或 取消点赞记录
            {
                //进行点赞 status 置 1
                redisTemplate.opsForHash().put(RedisConstans.USER_LIKE_COMMENT_KEY, String.valueOf(userId + "::" + entityId),"1");
                associationOperation(RedisConstans.USER_COMMENT_TOTAL_LIKED_COUNT_KEY,likedUserId);
                associationOperation(RedisConstans.COMMENT_TOTAL_LIKE_COUNT_KEY,entityId);
                return AjaxResult.success("点赞成功");
            }else if(status == null){ // 未存在 点赞记录
                //缓存点赞记录 status 为 1
                 redisTemplate.opsForHash().put(RedisConstans.USER_LIKE_COMMENT_KEY, String.valueOf(userId + "::" + entityId),"1");
                associationOperation(RedisConstans.USER_COMMENT_TOTAL_LIKED_COUNT_KEY,likedUserId);
                associationOperation(RedisConstans.COMMENT_TOTAL_LIKE_COUNT_KEY,entityId);
                return AjaxResult.success("点赞成功");
            }else {// status = "1"， 已点赞
                return  AjaxResult.error("已点赞，无需重复点赞！");
            }
        }
    }

    /**
     * 取消点赞
     * @param entityId   被点赞对象ID
     * @param likedUserId 被点赞用户ID
     * @param userId 点赞用户
     * @param eneityType 实体类型
     */
    @Override
    public AjaxResult doUnPraise(Long entityId, Long likedUserId, Long userId, Integer eneityType) {
        if(eneityType == 1){//article
            Object status = redisTemplate.opsForHash().get(RedisConstans.USER_LIKE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId));
            if(status != null && status != "0"){
                //修改 点赞状态为 0
                redisTemplate.opsForHash().put(RedisConstans.USER_LIKE_ARTICLE_KEY, String.valueOf(userId + "::" + entityId),"0");
                //被点赞用户的 总被点赞数-1
                unLikeAssociation(RedisConstans.USER_ARTICLE_TOTAL_LIKED_COUNT_KEY,likedUserId);
                // 该文章 总被点赞数 -1
                unLikeAssociation(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY,entityId);
                return  AjaxResult.success("取消点赞成功！");
            }else{// 无点赞记录
                return AjaxResult.error("该用户未对本文章点赞！");
            }
        }else {// comment
            Object status = redisTemplate.opsForHash().get(RedisConstans.USER_LIKE_COMMENT_KEY, String.valueOf(userId + "::" + entityId));
            if(status != null && status != "0"){
                //修改 点赞状态为 0
                redisTemplate.opsForHash().put(RedisConstans.USER_LIKE_COMMENT_KEY, String.valueOf(userId + "::" + entityId),"0");
                //被点赞用户的 总被点赞数-1
                unLikeAssociation(RedisConstans.USER_COMMENT_TOTAL_LIKED_COUNT_KEY,likedUserId);
                // 该文章 总被点赞数 -1
                unLikeAssociation(RedisConstans.COMMENT_TOTAL_LIKE_COUNT_KEY,entityId);
                return  AjaxResult.success("取消点赞成功！");
            }else{// 无点赞记录
                return AjaxResult.error("该用户未对本评论点赞！");
            }
        }
    }

    /**
     * 取消点赞 管理操作
     * @param key
     * @param id
     */
    private void unLikeAssociation(String key,Long id){
        Object count = redisTemplate.opsForHash().get(key, String.valueOf(id));
        Long total = Long.valueOf((String) count);
        redisTemplate.opsForHash().put(key, String.valueOf(id),String.valueOf(total-1));
    }

    /**
     * 统计entity  的点赞总数
     * @param entityId
     * @param eneityType 实体类型
     * @return
     */
    @Override
    public Long countTotalLike(Long entityId, Integer eneityType) {
        if(eneityType == 1){// article
            Object count = redisTemplate.opsForHash().get(RedisConstans.ARTICLE_TOTAL_LIKE_COUNT_KEY, String.valueOf(entityId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }else {// comment
            Object count = redisTemplate.opsForHash().get(RedisConstans.COMMENT_TOTAL_LIKE_COUNT_KEY, String.valueOf(entityId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }
    }

    /**
     * 统计 用户 entity总被点赞数
     * @param likedUserId
     * @param entityType  实体类型
     * @return
     */
    @Override
    public Long countUserLike(Long likedUserId, Integer entityType) {
        if(entityType == 1){// article
            Object count = redisTemplate.opsForHash().get(RedisConstans.USER_ARTICLE_TOTAL_LIKED_COUNT_KEY, String.valueOf(likedUserId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }else {// comment
            Object count = redisTemplate.opsForHash().get(RedisConstans.USER_COMMENT_TOTAL_LIKED_COUNT_KEY, String.valueOf(likedUserId));
            if(count != null){
                return Long.valueOf((String)count);
            }else {// count == null，没有记录
                return  0L;
            }
        }
    }

    /**
     * 获取 用户 点赞的 entity ids
     * @param userId 点赞用户ID
     * @param entityType
     * @return
     */
    @Override
    public List<Long> getUserLikeentitys(Long userId, Integer entityType) {
        if(entityType == 1){// article
            List<Long> articleIds = new ArrayList<>();
            Map<Object, Object> userLikeArticle = redisTemplate.opsForHash().entries(RedisConstans.USER_LIKE_ARTICLE_KEY);
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
            Map<Object, Object> userLikeComments = redisTemplate.opsForHash().entries(RedisConstans.USER_LIKE_COMMENT_KEY);
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
