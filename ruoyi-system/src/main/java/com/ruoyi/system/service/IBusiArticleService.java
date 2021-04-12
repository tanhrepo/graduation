package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusiArticle;

import java.util.List;

/**
 * 【文章】Service接口
 *
 * @author dfm
 * @date 2021-04-01
 */
public interface IBusiArticleService
{
    /**
     * 查询【文章】
     *
     * @param articleId 【文章】ID
     * @return 【文章】
     */
    public BusiArticle selectBusiArticleById(Long articleId);

    /**
     * 查询【文章】列表
     *
     * @param busiArticle 【文章】
     * @return 【文章】集合
     */
    public List<BusiArticle> selectBusiArticleList(BusiArticle busiArticle);



    /**
     * 查询视频区【文章】列表
     *
     * @return 【文章】集合
     */
    public List<BusiArticle> selectVedioBusiArticleList();

    /**
     * 查询图文区【文章】列表
     *
     * @return 【文章】集合
     */
    public List<BusiArticle> selectPhotoBusiArticleList();

    /**
     * 新增【文章】
     *
     * @param busiArticle 【文章】
     * @return 结果
     */
    public int insertBusiArticle(BusiArticle busiArticle);

    /**
     * 修改【文章】
     *
     * @param busiArticle 【文章】
     * @return 结果
     */
    public int updateBusiArticle(BusiArticle busiArticle);

    /**
     * 批量删除【文章】
     *
     * @param articleIds 需要删除的【文章】ID
     * @return 结果
     */
    public int deleteBusiArticleByIds(Long[] articleIds);

    /**
     * 删除【文章】信息
     *
     * @param articleId 【文章】ID
     * @return 结果
     */
    public int deleteBusiArticleById(Long articleId);
}
