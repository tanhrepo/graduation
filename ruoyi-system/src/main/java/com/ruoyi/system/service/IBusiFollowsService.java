package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BusiFollows;

/**
 * 关注信息Service接口
 * 
 * @author dfm
 * @date 2021-04-26
 */
public interface IBusiFollowsService 
{
    /**
     * 查询关注信息
     * 
     * @param id 关注信息ID
     * @return 关注信息
     */
    public BusiFollows selectBusiFollowsById(Long id);

    /**
     * 查询关注信息列表
     * 
     * @param busiFollows 关注信息
     * @return 关注信息集合
     */
    public List<BusiFollows> selectBusiFollowsList(BusiFollows busiFollows);

    /**
     * 新增关注信息
     * 
     * @param busiFollows 关注信息
     * @return 结果
     */
    public int insertBusiFollows(BusiFollows busiFollows);

    /**
     * 修改关注信息
     * 
     * @param busiFollows 关注信息
     * @return 结果
     */
    public int updateBusiFollows(BusiFollows busiFollows);

    /**
     * 批量删除关注信息
     * 
     * @param ids 需要删除的关注信息ID
     * @return 结果
     */
    public int deleteBusiFollowsByIds(Long[] ids);

    /**
     * 删除关注信息信息
     * 
     * @param id 关注信息ID
     * @return 结果
     */
    public int deleteBusiFollowsById(Long id);
}
