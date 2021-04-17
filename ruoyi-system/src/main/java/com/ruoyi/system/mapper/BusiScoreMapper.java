package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BusiScore;

/**
 * 推荐得分Mapper接口
 * 
 * @author dfm
 * @date 2021-04-17
 */
public interface BusiScoreMapper 
{
    /**
     * 查询推荐得分
     * 
     * @param id 推荐得分ID
     * @return 推荐得分
     */
    public BusiScore selectBusiScoreById(Long id);

    /**
     * 查询推荐得分列表
     * 
     * @param busiScore 推荐得分
     * @return 推荐得分集合
     */
    public List<BusiScore> selectBusiScoreList(BusiScore busiScore);

    /**
     * 新增推荐得分
     * 
     * @param busiScore 推荐得分
     * @return 结果
     */
    public int insertBusiScore(BusiScore busiScore);

    /**
     * 修改推荐得分
     * 
     * @param busiScore 推荐得分
     * @return 结果
     */
    public int updateBusiScore(BusiScore busiScore);

    /**
     * 删除推荐得分
     * 
     * @param id 推荐得分ID
     * @return 结果
     */
    public int deleteBusiScoreById(Long id);

    /**
     * 批量删除推荐得分
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusiScoreByIds(Long[] ids);
}
