package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusiFollowsMapper;
import com.ruoyi.system.domain.BusiFollows;
import com.ruoyi.system.service.IBusiFollowsService;

/**
 * 关注信息Service业务层处理
 * 
 * @author dfm
 * @date 2021-04-26
 */
@Service
public class BusiFollowsServiceImpl implements IBusiFollowsService 
{
    @Autowired
    private BusiFollowsMapper busiFollowsMapper;

    /**
     * 查询关注信息
     * 
     * @param id 关注信息ID
     * @return 关注信息
     */
    @Override
    public BusiFollows selectBusiFollowsById(Long id)
    {
        return busiFollowsMapper.selectBusiFollowsById(id);
    }

    /**
     * 查询关注信息列表
     * 
     * @param busiFollows 关注信息
     * @return 关注信息
     */
    @Override
    public List<BusiFollows> selectBusiFollowsList(BusiFollows busiFollows)
    {
        return busiFollowsMapper.selectBusiFollowsList(busiFollows);
    }

    /**
     * 新增关注信息
     * 
     * @param busiFollows 关注信息
     * @return 结果
     */
    @Override
    public int insertBusiFollows(BusiFollows busiFollows)
    {
        busiFollows.setCreateTime(DateUtils.getNowDate());
        return busiFollowsMapper.insertBusiFollows(busiFollows);
    }

    /**
     * 修改关注信息
     * 
     * @param busiFollows 关注信息
     * @return 结果
     */
    @Override
    public int updateBusiFollows(BusiFollows busiFollows)
    {
        busiFollows.setUpdateTime(DateUtils.getNowDate());
        return busiFollowsMapper.updateBusiFollows(busiFollows);
    }

    /**
     * 批量删除关注信息
     * 
     * @param ids 需要删除的关注信息ID
     * @return 结果
     */
    @Override
    public int deleteBusiFollowsByIds(Long[] ids)
    {
        return busiFollowsMapper.deleteBusiFollowsByIds(ids);
    }

    /**
     * 删除关注信息信息
     * 
     * @param id 关注信息ID
     * @return 结果
     */
    @Override
    public int deleteBusiFollowsById(Long id)
    {
        return busiFollowsMapper.deleteBusiFollowsById(id);
    }
}
