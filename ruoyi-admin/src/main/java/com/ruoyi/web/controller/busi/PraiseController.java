package com.ruoyi.web.controller.busi;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BusiScore;
import com.ruoyi.system.service.IBusiOperationService;
import com.ruoyi.system.service.IBusiScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 点赞功能 控制层
 * @author dfm
 * @create 2021-04-16 18:45
 */
@Api("点赞模块")
@RestController
@RequestMapping("/system/praise")
public class PraiseController {

    @Resource
    IBusiPraiseService praiseServicel;

    @Autowired
    private IBusiScoreService scoreService;

    /**
     *用户点赞某篇文章
     * @param articleId 被点赞文章id
     * @param likedUserId 被点赞用户id
     * @param userId 点赞用户id
     * @return
     */
    @ApiOperation("用户点赞某篇文章")
    @GetMapping("likeArticle")
    public AjaxResult  likeArticle(Long articleId,Long likedUserId,Long userId){
        AjaxResult ajaxResult = praiseServicel.doPraise(articleId, likedUserId, userId, 1);
        if(ajaxResult.get("code").equals(200)){
            BusiScore busiScore = new BusiScore();
            busiScore.setUid(userId);
            busiScore.setAid(articleId);
            //检测是否 有其他行为打分
            List<BusiScore> busiScores = scoreService.selectBusiScoreList(busiScore);//若成功 只会返回一个对象
            busiScore.setTs(DateUtils.getNowDate());//不能让 ts参与 查询
            if(busiScore != null && busiScores.size() == 1){//有一条记录 则 修改
                busiScore.setScore(busiScores.get(0).getScore()+4);// 原得分基础 +4
                scoreService.updateBusiScore(busiScore);
            }else {// 没有打分记录 则 新增
                busiScore.setScore(4);
                scoreService.insertBusiScore(busiScore);
            }
        }
        return ajaxResult;
    }

    /**
     *用户点赞某篇评论
     * @param commentId 被点赞文章id
     * @param likedUserId 被点赞用户id
     * @param userId 点赞用户id
     * @return
     */
    @ApiOperation("用户点赞某篇评论")
    @GetMapping("likeComment")
    public AjaxResult  likeComment(Long commentId,Long likedUserId,Long userId){
        AjaxResult ajaxResult = praiseServicel.doPraise(commentId, likedUserId, userId, 2);
        return ajaxResult;
    }

    @ApiOperation("用户取消点赞某篇文章")
    @GetMapping("unLikeArticle")
    public AjaxResult  unLikeArticle(Long articleId,Long likedUserId,Long userId){
        AjaxResult ajaxResult = praiseServicel.doUnPraise(articleId, likedUserId, userId, 1);
        if(ajaxResult.get("code").equals(String.valueOf(200))){
            BusiScore busiScore = new BusiScore();
            busiScore.setUid(userId);
            busiScore.setAid(articleId);
            //检测是否 有其他行为打分
            List<BusiScore> busiScores = scoreService.selectBusiScoreList(busiScore);//若成功 只会返回一个对象
            busiScore.setTs(DateUtils.getNowDate());//不能让 ts参与 查询
            busiScore.setScore(busiScores.get(0).getScore()-4);
            scoreService.updateBusiScore(busiScore);
//            if(busiScore != null && busiScores.size() == 1){//有一条记录 则 修改
//                busiScore.setScore(busiScores.get(0).getScore()-4);// 原得分基础 +4
//                scoreService.updateBusiScore(busiScore);
//            }else {// 没有打分记录 则 新增
//                busiScore.setScore(4);
//                scoreService.insertBusiScore(busiScore);
//            }
        }
        return ajaxResult;
    }

    @ApiOperation("用户取消点赞某条评论")
    @GetMapping("unLikeComment")
    public AjaxResult  unLikeComment(Long articleId,Long likedUserId,Long userId){
        AjaxResult ajaxResult = praiseServicel.doUnPraise(articleId, likedUserId, userId, 2);
        return ajaxResult;
    }

    @ApiOperation("统计文章的点赞总数")
    @GetMapping("countTotalArticle")
    public AjaxResult countTotalLike(Long articleId){
        Long count = praiseServicel.countTotalLike(articleId, 1);
        return AjaxResult.success(count);
    }

    @ApiOperation("统计评论的点赞总数")
    @GetMapping("countTotalComment")
    public AjaxResult countTotalComment(Long commentId){
        Long count = praiseServicel.countTotalLike(commentId, 2);
        return AjaxResult.success(count);
    }


    /**
     * 获取用户点赞的文章ids
     *
     * @param userId 点赞用户ID
     * @return
     */
    @ApiOperation("获取用户点赞的文章ids")
    @GetMapping("userArticleLikes")
    public AjaxResult getUserLikeArticles(Long userId){
        List<Long> userLikeentitys = praiseServicel.getUserLikeentitys(userId, 1);
        return AjaxResult.success(userLikeentitys);
    }

    /**
     * 获取用户点赞的comment ids
     *
     * @param userId 点赞用户ID
     * @return
     */
    @ApiOperation("获取用户点赞的comment ids")
    @GetMapping("userCommentleLikes")
    public AjaxResult getUserLikeComments(Long userId){
        List<Long> userLikeentitys = praiseServicel.getUserLikeentitys(userId, 2);
        return AjaxResult.success(userLikeentitys);
    }

}
