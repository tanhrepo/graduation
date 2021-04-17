package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BusiOperation;

/**
 * 用户操作Service接口
 * 
 * @author dfm
 * @date 2021-04-17
 */
public interface IBusiOperationService 
{
    /**
     * 查询用户操作
     * 
     * @param id 用户操作ID
     * @return 用户操作
     */
    public BusiOperation selectBusiOperationById(Long id);

    /**
     * 查询用户操作列表
     * 
     * @param busiOperation 用户操作
     * @return 用户操作集合
     */
    public List<BusiOperation> selectBusiOperationList(BusiOperation busiOperation);

    /**
     * 新增用户操作
     * 
     * @param busiOperation 用户操作
     * @return 结果
     */
    public int insertBusiOperation(BusiOperation busiOperation);

    /**
     * 修改用户操作
     * 
     * @param busiOperation 用户操作
     * @return 结果
     */
    public int updateBusiOperation(BusiOperation busiOperation);

    /**
     * 批量删除用户操作
     * 
     * @param ids 需要删除的用户操作ID
     * @return 结果
     */
    public int deleteBusiOperationByIds(Long[] ids);

    /**
     * 删除用户操作信息
     * 
     * @param id 用户操作ID
     * @return 结果
     */
    public int deleteBusiOperationById(Long id);
}
