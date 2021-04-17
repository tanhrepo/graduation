package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusiScoreMapper;
import com.ruoyi.system.domain.BusiScore;
import com.ruoyi.system.service.IBusiScoreService;

/**
 * 推荐得分Service业务层处理
 * 
 * @author dfm
 * @date 2021-04-17
 */
@Service
public class BusiScoreServiceImpl implements IBusiScoreService 
{
    @Autowired
    private BusiScoreMapper busiScoreMapper;

    /**
     * 查询推荐得分
     * 
     * @param id 推荐得分ID
     * @return 推荐得分
     */
    @Override
    public BusiScore selectBusiScoreById(Long id)
    {
        return busiScoreMapper.selectBusiScoreById(id);
    }

    /**
     * 查询推荐得分列表
     * 
     * @param busiScore 推荐得分
     * @return 推荐得分
     */
    @Override
    public List<BusiScore> selectBusiScoreList(BusiScore busiScore)
    {
        return busiScoreMapper.selectBusiScoreList(busiScore);
    }

    /**
     * 新增推荐得分
     * 
     * @param busiScore 推荐得分
     * @return 结果
     */
    @Override
    public int insertBusiScore(BusiScore busiScore)
    {
        return busiScoreMapper.insertBusiScore(busiScore);
    }

    /**
     * 修改推荐得分
     * 
     * @param busiScore 推荐得分
     * @return 结果
     */
    @Override
    public int updateBusiScore(BusiScore busiScore)
    {
        return busiScoreMapper.updateBusiScore(busiScore);
    }

    /**
     * 批量删除推荐得分
     * 
     * @param ids 需要删除的推荐得分ID
     * @return 结果
     */
    @Override
    public int deleteBusiScoreByIds(Long[] ids)
    {
        return busiScoreMapper.deleteBusiScoreByIds(ids);
    }

    /**
     * 删除推荐得分信息
     * 
     * @param id 推荐得分ID
     * @return 结果
     */
    @Override
    public int deleteBusiScoreById(Long id)
    {
        return busiScoreMapper.deleteBusiScoreById(id);
    }
}
