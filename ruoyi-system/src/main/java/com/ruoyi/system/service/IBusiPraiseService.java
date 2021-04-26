package com.ruoyi.system.service;


import com.ruoyi.common.core.domain.AjaxResult;

import java.util.List;

/**
 * @author dfm
 * @create 2021-04-16 19:31
 */

public interface IBusiPraiseService {
    /**
     * 用户点赞文章 或 评论
     *
     * @param likedUserId 被点赞用户ID
     * @param userId 点赞用户
     * @param entityId   被点赞对象ID
     * @param eneityType  实体类型
     */
     AjaxResult doPraise(Long entityId, Long likedUserId, Long userId, Integer eneityType);

    /**
     * 取消点赞
     *
     * @param likedUserId 被点赞用户ID
     * @param userId 点赞用户
     * @param eneityType 实体类型
     * @param entityId   被点赞对象ID
     */
    AjaxResult doUnPraise(Long entityId, Long likedUserId, Long userId,Integer eneityType);

    /**
     * 统计(文章 或 评论) 总被点赞数
     *
     * @param entityId
     * @param eneityType 实体类型
     * @return
     */
    Long countTotalLike(Long entityId,Integer eneityType);

    /**
     * 统计用户总的文章（评论）点赞数
     * @param entityType  实体类型
     * @param likedUserId
     * @return
     */
    Long countUserLike(Long likedUserId,Integer entityType);

    /**
     * 获取用户点赞的实体对象
     *
     * @param userId 点赞用户ID
     * @return
     */
    List<Long> getUserLikeentitys(Long userId,Integer entityType);
}
