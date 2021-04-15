package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.BusiLable;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author dfm
 * @date 2021-04-15
 */
public interface BusiLableMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param lableId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public BusiLable selectBusiLableById(Long lableId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param busiLable 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<BusiLable> selectBusiLableList(BusiLable busiLable);

    /**
     * 新增【请填写功能名称】
     * 
     * @param busiLable 【请填写功能名称】
     * @return 结果
     */
    public int insertBusiLable(BusiLable busiLable);

    /**
     * 修改【请填写功能名称】
     * 
     * @param busiLable 【请填写功能名称】
     * @return 结果
     */
    public int updateBusiLable(BusiLable busiLable);

    /**
     * 删除【请填写功能名称】
     * 
     * @param lableId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBusiLableById(Long lableId);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param lableIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusiLableByIds(Long[] lableIds);
}
