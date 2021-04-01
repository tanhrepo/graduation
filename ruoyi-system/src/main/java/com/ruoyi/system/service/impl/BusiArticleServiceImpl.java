package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BusiArticle;
import com.ruoyi.system.mapper.BusiArticleMapper;
import com.ruoyi.system.service.IBusiArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author dfm
 * @date 2021-04-01
 */
@Service
public class BusiArticleServiceImpl implements IBusiArticleService
{
    @Autowired
    private BusiArticleMapper busiArticleMapper;

    /**
     * 查询【请填写功能名称】
     *
     * @param articleId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BusiArticle selectBusiArticleById(Long articleId)
    {
        return busiArticleMapper.selectBusiArticleById(articleId);
    }

    /**
     * 查询【请填写功能名称】列表
     *
     * @param busiArticle 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BusiArticle> selectBusiArticleList(BusiArticle busiArticle)
    {
        return busiArticleMapper.selectBusiArticleList(busiArticle);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param busiArticle 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBusiArticle(BusiArticle busiArticle)
    {
        busiArticle.setCreateTime(DateUtils.getNowDate());
        return busiArticleMapper.insertBusiArticle(busiArticle);
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param busiArticle 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBusiArticle(BusiArticle busiArticle)
    {
        busiArticle.setUpdateTime(DateUtils.getNowDate());
        return busiArticleMapper.updateBusiArticle(busiArticle);
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param articleIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusiArticleByIds(Long[] articleIds)
    {
        return busiArticleMapper.deleteBusiArticleByIds(articleIds);
    }

    /**
     * 删除【请填写功能名称】信息
     *
     * @param articleId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusiArticleById(Long articleId)
    {
        return busiArticleMapper.deleteBusiArticleById(articleId);
    }
}
