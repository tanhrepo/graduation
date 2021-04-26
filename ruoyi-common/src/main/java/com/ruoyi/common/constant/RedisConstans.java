package com.ruoyi.common.constant;

/**
 * @author dfm
 * @create 2021-04-25 12:28
 */
public class RedisConstans {
    /**
     * 文章点赞总数key
     */
    public static final String ARTICLE_TOTAL_LIKE_COUNT_KEY = "praise:article.total.like.count";

    /**
     * 评论点赞总数key
     */
    public static final String COMMENT_TOTAL_LIKE_COUNT_KEY = "praise:comment.total.like.count";

    /**
     * 用户点赞文章key
     */
    public static final String USER_LIKE_ARTICLE_KEY = "praise:user.like.article";

    //用户点赞评论 key
    public static final String USER_LIKE_COMMENT_KEY = "praise:user.like.comment";

    /**
     * 用户文章 被点赞总数
     */
    public static final String USER_ARTICLE_TOTAL_LIKED_COUNT_KEY= "praise:user.article.total.liked.count";
    /**
     * 用户点赞评论总数
     */
    public static final String USER_COMMENT_TOTAL_LIKED_COUNT_KEY = "praise:user.comment.total.liked.count";


    /**
     * 文章被踩 总数key
     */
    public static final String ARTICLE_TOTAL_TRAMPLE_COUNT_KEY = "trample:article.total.trample.count";

    /**
     * 评论被踩 总数key
     */
    public static final String COMMENT_TOTAL_TRAMPLE_COUNT_KEY = "trample:comment.total.trample.count";

    /**
     * 用户踩文章key
     */
    public static final String USER_TRAMPLE_ARTICLE_KEY = "trample:user.trample.article";

    //用户踩评论 key
    public static final String USER_TRAMPLE_COMMENT_KEY = "trample:user.trample.comment";

    /**
     * 用户文章 被被踩总数
     */
    public static final String USER_ARTICLE_TOTAL_TRAMPLED_COUNT_KEY= "trample:user.article.total.trampled.count";
    /**
     * 用户被踩 评论总数
     */
    public static final String USER_COMMENT_TOTAL_TRAMPLED_COUNT_KEY = "trample:user.comment.total.trampled.count";

}
