package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusiArticle;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author dfm
 * @date 2021-04-01
 */
public interface BusiArticleMapper
{
    /**
     * 查询【请填写功能名称】
     *
     * @param articleId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public BusiArticle selectBusiArticleById(Long articleId);

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busiArticle 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BusiArticle> selectBusiArticleList(BusiArticle busiArticle);

    /**
     * 新增【请填写功能名称】
     *
     * @param busiArticle 【请填写功能名称】
     * @return 结果
     */
    public int insertBusiArticle(BusiArticle busiArticle);

    /**
     * 修改【请填写功能名称】
     *
     * @param busiArticle 【请填写功能名称】
     * @return 结果
     */
    public int updateBusiArticle(BusiArticle busiArticle);

    /**
     * 删除【请填写功能名称】
     *
     * @param articleId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusiArticleById(Long articleId);

    /**
     * 批量删除【请填写功能名称】
     *
     * @param articleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusiArticleByIds(Long[] articleIds);
}
