package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusiOperationMapper;
import com.ruoyi.system.domain.BusiOperation;
import com.ruoyi.system.service.IBusiOperationService;

/**
 * 用户操作Service业务层处理
 * 
 * @author dfm
 * @date 2021-04-17
 */
@Service
public class BusiOperationServiceImpl implements IBusiOperationService 
{
    @Autowired
    private BusiOperationMapper busiOperationMapper;

    /**
     * 查询用户操作
     * 
     * @param id 用户操作ID
     * @return 用户操作
     */
    @Override
    public BusiOperation selectBusiOperationById(Long id)
    {
        return busiOperationMapper.selectBusiOperationById(id);
    }

    /**
     * 查询用户操作列表
     * 
     * @param busiOperation 用户操作
     * @return 用户操作
     */
    @Override
    public List<BusiOperation> selectBusiOperationList(BusiOperation busiOperation)
    {
        return busiOperationMapper.selectBusiOperationList(busiOperation);
    }

    /**
     * 新增用户操作
     * 
     * @param busiOperation 用户操作
     * @return 结果
     */
    @Override
    public int insertBusiOperation(BusiOperation busiOperation)
    {
        busiOperation.setCreateTime(DateUtils.getNowDate());
        return busiOperationMapper.insertBusiOperation(busiOperation);
    }

    /**
     * 修改用户操作
     * 
     * @param busiOperation 用户操作
     * @return 结果
     */
    @Override
    public int updateBusiOperation(BusiOperation busiOperation)
    {
        busiOperation.setUpdateTime(DateUtils.getNowDate());
        return busiOperationMapper.updateBusiOperation(busiOperation);
    }

    /**
     * 批量删除用户操作
     * 
     * @param ids 需要删除的用户操作ID
     * @return 结果
     */
    @Override
    public int deleteBusiOperationByIds(Long[] ids)
    {
        return busiOperationMapper.deleteBusiOperationByIds(ids);
    }

    /**
     * 删除用户操作信息
     * 
     * @param id 用户操作ID
     * @return 结果
     */
    @Override
    public int deleteBusiOperationById(Long id)
    {
        return busiOperationMapper.deleteBusiOperationById(id);
    }
}
