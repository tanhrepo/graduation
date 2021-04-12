package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.BusiArticle;
import com.ruoyi.system.mapper.BusiArticleMapper;
import com.ruoyi.system.service.IBusiArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 【文章】Service业务层处理
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
     * 查询【文章】
     *
     * @param articleId 【文章】ID
     * @return 【文章】
     */
    @Override
    public BusiArticle selectBusiArticleById(Long articleId)
    {
        return busiArticleMapper.selectBusiArticleById(articleId);
    }

    /**
     * 查询【文章】列表
     *
     * @param busiArticle 【文章】
     * @return 【文章】
     */
    @Override
    public List<BusiArticle> selectBusiArticleList(BusiArticle busiArticle)
    {
        return busiArticleMapper.selectBusiArticleList(busiArticle);
    }


    /**
     * 查询图文区 文章列表
     * @return
     */
    @Override
    public List<BusiArticle> selectPhotoBusiArticleList() {
        return busiArticleMapper.selectPhotoBusiArticleList();
    }

    /**
     * 查询视频区 文章列表
     * @return
     */
    @Override
    public List<BusiArticle> selectVedioBusiArticleList() {
        return busiArticleMapper.selectVedioBusiArticleList();
    }
    /**
     * 新增【文章】
     *
     * @param busiArticle 【文章】
     * @return 结果
     */
    @Override
    public int insertBusiArticle(BusiArticle busiArticle)
    {
        busiArticle.setCreateTime(DateUtils.getNowDate());
        return busiArticleMapper.insertBusiArticle(busiArticle);
    }

    /**
     * 修改【文章】
     *
     * @param busiArticle 【文章】
     * @return 结果
     */
    @Override
    public int updateBusiArticle(BusiArticle busiArticle)
    {
        busiArticle.setUpdateTime(DateUtils.getNowDate());
        return busiArticleMapper.updateBusiArticle(busiArticle);
    }

    /**
     * 批量删除【文章】
     *
     * @param articleIds 需要删除的【文章】ID
     * @return 结果
     */
    @Override
    public int deleteBusiArticleByIds(Long[] articleIds)
    {
        return busiArticleMapper.deleteBusiArticleByIds(articleIds);
    }

    /**
     * 删除【文章】信息
     *
     * @param articleId 【文章】ID
     * @return 结果
     */
    @Override
    public int deleteBusiArticleById(Long articleId)
    {
        return busiArticleMapper.deleteBusiArticleById(articleId);
    }
}
