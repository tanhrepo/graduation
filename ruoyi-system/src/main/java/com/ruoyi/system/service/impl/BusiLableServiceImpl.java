package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusiLableMapper;
import com.ruoyi.system.domain.BusiLable;
import com.ruoyi.system.service.IBusiLableService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author dfm
 * @date 2021-04-15
 */
@Service
public class BusiLableServiceImpl implements IBusiLableService 
{
    @Autowired
    private BusiLableMapper busiLableMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param lableId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public BusiLable selectBusiLableById(Long lableId)
    {
        return busiLableMapper.selectBusiLableById(lableId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param busiLable 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<BusiLable> selectBusiLableList(BusiLable busiLable)
    {
        return busiLableMapper.selectBusiLableList(busiLable);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param busiLable 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBusiLable(BusiLable busiLable)
    {
        busiLable.setCreateTime(DateUtils.getNowDate());
        return busiLableMapper.insertBusiLable(busiLable);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param busiLable 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBusiLable(BusiLable busiLable)
    {
        busiLable.setUpdateTime(DateUtils.getNowDate());
        return busiLableMapper.updateBusiLable(busiLable);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param lableIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusiLableByIds(Long[] lableIds)
    {
        return busiLableMapper.deleteBusiLableByIds(lableIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param lableId 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBusiLableById(Long lableId)
    {
        return busiLableMapper.deleteBusiLableById(lableId);
    }
}
